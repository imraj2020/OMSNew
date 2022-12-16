package com.cse.oms.ui.createorder.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.cse.oms.CreateOrderRoomDatabase.database.OrderDatabase;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.R;
import com.cse.oms.ui.createorder.Adapter.DraftAdapter;
import com.cse.oms.ui.createorder.MessageEvent;
import com.cse.oms.ui.createorder.model.ShowDraftOrder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShowDraftsDialog extends DialogFragment {
    private final ArrayList<DraftOrderModel> draftOrderModels = new ArrayList<>();
    ProgressBar progressbar;
    LinearLayout rootLayout;
    Context context;
    RecyclerView rvDrafts;
    DraftAdapter draftAdapter;
    private View rootView;
    private OrderDatabase orderDatabase;
    private int orderStatus;

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        showDraftOrders();
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(true)
                .setPositiveButton("Close", null)
                .create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
            }
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_show_drafts, null, false);
        rvDrafts = rootView.findViewById(R.id.rvDrafts);

        draftAdapter = new DraftAdapter(draftOrderModels, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvDrafts.setLayoutManager(layoutManager);
        rvDrafts.setAdapter(draftAdapter);
    }

    private void showDraftOrders() {
        orderDatabase = Room.databaseBuilder(getContext(),
                OrderDatabase.class, Constants.DATABASE_NAME).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DraftOrderModel> allOrders = orderDatabase.daoAccess().getAllOrders(orderStatus);
                if (allOrders.isEmpty()) {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "No Drafts saved", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    draftOrderModels.addAll(allOrders);
                    draftAdapter.notifyDataSetChanged();
                }
            }
        }).start();
    }


    private void showProgressBar(boolean visible) {
        if (visible) {
            progressbar.setVisibility(View.VISIBLE);
            rootLayout.setAlpha(Constants.PROGRESSBAR_ALPHA);
        } else {
            progressbar.setVisibility(View.GONE);
            rootLayout.setAlpha(Constants.PROGRESSBAR_AFTER_FINISH_ALPHA);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (event.isUpdate())
            dismiss();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DraftOrderModel draftOrderModel) {
        draftOrderModels.remove(draftOrderModel);
        draftAdapter.notifyDataSetChanged();

        if (draftOrderModels.isEmpty())
            dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ShowDraftOrder showDraftOrder) {
        // To delete the draft
        if (showDraftOrder.isDelete()) {
            draftOrderModels.remove(showDraftOrder.getDraftOrderModel());
            draftAdapter.notifyDataSetChanged();
        }
        // To show the drafts in home
        else {
            EventBus.getDefault().post(showDraftOrder.getDraftOrderModel());
        }

        if (draftOrderModels.isEmpty())
            dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (orderDatabase != null)
            orderDatabase.close();
    }
}
