package com.cse.oms.ui.createorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.cse.oms.CreateOrderRoomDatabase.database.OrderDatabase;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.R;
import com.cse.oms.ui.createorder.MessageEvent;
import com.cse.oms.ui.createorder.Utils.Constants;
import com.cse.oms.ui.createorder.Utils.Utilities;
import com.cse.oms.ui.createorder.model.ShowDraftOrder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.DraftViewHolder> {
    private final Context context;
    private final List<DraftOrderModel> items;

    public DraftAdapter(List<DraftOrderModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public DraftViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drafts, parent, false);
        return new DraftViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DraftViewHolder holder, int position) {
        DraftOrderModel item = items.get(position);
        holder.set(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class DraftViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderId, tvCustomerName;
        ImageView imgAddDrafts, ivDelete;

        public DraftViewHolder(View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            imgAddDrafts = itemView.findViewById(R.id.ivAddDrafts);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }

        public void set(final DraftOrderModel item) {
            tvCustomerName.setText(item.getId() + ". " + item.getCustomerName());
            imgAddDrafts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowDraftOrder showDraftOrder = new ShowDraftOrder();
                    showDraftOrder.setDraftOrderModel(item);
                    showDraftOrder.setDelete(false);
                    EventBus.getDefault().post(new MessageEvent(true));
                    EventBus.getDefault().post(showDraftOrder);
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final OrderDatabase orderDatabase = Room.databaseBuilder(context,
                            OrderDatabase.class, Constants.DATABASE_NAME).build();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                orderDatabase.daoAccess().delete(item);
                            } catch (Exception e) {
                                Utilities.showLogcatMessage(e.getMessage());
                            }
                            ShowDraftOrder showDraftOrder = new ShowDraftOrder();
                            showDraftOrder.setDraftOrderModel(item);
                            showDraftOrder.setDelete(true);
                            EventBus.getDefault().post(showDraftOrder);
                        }
                    }).start();
                    //Toast.makeText(context.getApplicationContext(), "This Item is Deleted", Toast.LENGTH_SHORT).show();

                    orderDatabase.close();
                }
            });
        }
    }
}