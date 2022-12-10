package com.cse.oms.ui.Drafts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftProductModel;
import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.databinding.DraftsFragmentBinding;
import com.cse.oms.ui.createorder.Adapter.AddedProductAdapter;
import com.cse.oms.ui.createorder.Adapter.DraftAdapter;
import com.cse.oms.ui.createorder.Adapter.SaleProductAdapter;
import com.cse.oms.ui.createorder.MessageEvent;
import com.cse.oms.ui.createorder.UiModificationEvent;
import com.cse.oms.ui.createorder.Utils.Constants;
import com.cse.oms.ui.createorder.Utils.Utilities;
import com.cse.oms.ui.createorder.model.OrderProductsModel;
import com.cse.oms.ui.createorder.model.ShowDraftOrder;
import com.cse.oms.ui.createorder.model.SubmitOrder;
import com.cse.oms.ui.createorder.model.SubmitOrderResponce;
import com.cse.oms.ui.createorder.roomdb.OrderDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftsFragment extends Fragment {

    private final ArrayList<DraftOrderModel> draftOrderModels = new ArrayList<>();
    ProgressBar progressbar;
    LinearLayout rootLayout;
    Context context;
    RecyclerView rvDrafts;
    DraftAdapter draftAdapter;
    ArrayList<OrderProductsModel> productsModels = new ArrayList<>();
    SaleProductAdapter saleProductAdapter;
    ArrayList<OrderProductsModel> addedProducts = new ArrayList<>();
    AddedProductAdapter addedProductAdapter;
    String CustomerID, OrderDate, DelivaryDate, EntryTime, Note;
    private DraftsFragmentBinding binding;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DraftsFragmentBinding.inflate(inflater);
        initViews();
        initRecyclerView();
        showDraftOrders();
        Onclick();
        Productlist();
        return binding.getRoot();

    }

    private void initViews() {
        draftAdapter = new DraftAdapter(draftOrderModels, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvDrafts.setLayoutManager(layoutManager);
        binding.rvDrafts.setAdapter(draftAdapter);
    }

    private void initRecyclerView() {
        try {
            saleProductAdapter = new SaleProductAdapter(productsModels, getContext());
            addedProductAdapter = new AddedProductAdapter(addedProducts, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.rvProducts.setLayoutManager(layoutManager);
            binding.rvProducts.setAdapter(saleProductAdapter);

            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
            binding.rvAddedProducts.setLayoutManager(layoutManager1);
            binding.rvAddedProducts.setAdapter(addedProductAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Onclick() {
        binding.tvProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutAddProductsBillPreview.setVisibility(View.GONE);
                binding.rvProducts.setVisibility(View.VISIBLE);
                binding.llButton.setVisibility(View.GONE);
            }
        });
        binding.tvOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.rvProducts.setVisibility(View.GONE);
                binding.layoutAddProductsBillPreview.setVisibility(View.VISIBLE);
                binding.llButton.setVisibility(View.VISIBLE);
            }
        });
        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBtnOnClick();
            }
        });
    }

    private void showDraftOrders() {
        orderDatabase = Room.databaseBuilder(getContext(),
                OrderDatabase.class, Constants.DATABASE_NAME).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DraftOrderModel> allOrders = orderDatabase.daoAccess().getAllOrders(Constants.SALE_DRAFT_ORDER);
                if (allOrders.isEmpty()) {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "No Drafts saved", Toast.LENGTH_SHORT).show();
                                // dismiss();
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

    /*-------------------Product List----------------------*/
    public void Productlist() {

        Call<List<OrderProductsModel>> call = ApiClient.getUserService().getOrderProduct();

        call.enqueue(new Callback<List<OrderProductsModel>>() {
            @Override
            public void onResponse(Call<List<OrderProductsModel>> call, Response<List<OrderProductsModel>> response) {

                if (response.body() != null) {
                    productsModels.clear();
                    productsModels.addAll(response.body());
                    saleProductAdapter.notifyDataSetChanged();
                    // Toast.makeText(getContext(), "response.body()", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<OrderProductsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < addedProducts.size(); i++) {
            total += addedProducts.get(i).getAmount();
        }
        binding.etNetPayment.setText(String.format("%.2f", total));
        addedProductAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (event.isUpdate())
            Toast.makeText(context, "No Item in Drafts", Toast.LENGTH_SHORT).show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DraftOrderModel draftOrderModel) {
        draftOrderModels.remove(draftOrderModel);
        draftAdapter.notifyDataSetChanged();

        if (draftOrderModels.isEmpty())
            Toast.makeText(context, "No Item in Drafts", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "No Item in Drafts", Toast.LENGTH_SHORT).show();
    }

    private void showLayoutAddProductsBillPreview(boolean visible) {
        TransitionSet set = new TransitionSet()
                .addTransition(new Fade())
                .setInterpolator(visible ? new LinearOutSlowInInterpolator() :
                        new FastOutLinearInInterpolator());

        TransitionManager.beginDelayedTransition(binding.layoutAddProductsBillPreview, set);
        binding.layoutAddProductsBillPreview.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        //  binding.btnClearAll.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    //Event Subscription
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setDrafts(final DraftOrderModel draftOrderModel) {
        binding.llProduct.setVisibility(View.VISIBLE);
        binding.layoutAddProductsBillPreview.setVisibility(View.VISIBLE);
        binding.rvProducts.setVisibility(View.GONE);
        binding.llDraftList.setVisibility(View.GONE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerID = draftOrderModel.getCustomerID();
                OrderDate = draftOrderModel.getOrderDate();
                DelivaryDate = draftOrderModel.getDeliveryDate();
                EntryTime = draftOrderModel.getEntryTime();
                Note = draftOrderModel.getNote();
                binding.tvCustomer.setText(draftOrderModel.getCustomerName());
                binding.etNetPayment.setText(String.valueOf(draftOrderModel.getAmount()));
                List<DraftProductModel> draftProductModels = orderDatabase.daoAccess().getProductsByOrder(draftOrderModel.getId());
                addedProducts.clear();
                for (DraftProductModel draftProductModel : draftProductModels) {
                    OrderProductsModel productsModel = new OrderProductsModel();
                    productsModel.setId(draftProductModel.getId());
                    productsModel.setName(draftProductModel.getName());
                    productsModel.setQuantity(draftProductModel.getQuantity());
                    productsModel.setAmount(draftProductModel.getAmount());
                    addedProducts.add(productsModel);
                }


                if (!addedProducts.isEmpty()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.llDraftList.setVisibility(View.GONE);
                            showLayoutAddProductsBillPreview(true);
                            addedProductAdapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        }).start();
    }

    public void submitBtnOnClick() {
        //showProgressBar(true);
        SubmitOrder submitOrder = new SubmitOrder();
        submitOrder.setOrderDetails(addedProducts);
        submitOrder.setCustomerId(CustomerID);
        submitOrder.setOrderDate(OrderDate);
        submitOrder.setDeliveryDate(DelivaryDate);
        submitOrder.setEntryDateTime(EntryTime);

        LoginResRoomDB database = LoginResRoomDB.getDbInstance(requireContext());
        List<LoginResInfo> list = database.loginResDAO().getAllData();
        int empid = list.get(0).getEmpId();
        int saleslineid = list.get(0).getSalesLineId();


        submitOrder.setEntryBy(empid);
        submitOrder.setNote(Note);
        submitOrder.setTerritoryId(Constants.TerritoryId);
        submitOrder.setSCId(String.valueOf(saleslineid));
        submitOrder.setOnBehalfOfEmpId(Constants.OnBehalfOfEmpId);
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        Utilities.showLogcatMessage("DATA" + submitOrder);
        Call<SubmitOrderResponce> SubmitOrderResponceCall = ApiClient.getUserService().SubmitOrder(submitOrder);
        SubmitOrderResponceCall.enqueue(new Callback<SubmitOrderResponce>() {
            @Override
            public void onResponse(Call<SubmitOrderResponce> call, Response<SubmitOrderResponce> response) {
                if (response.isSuccessful()) {

                    SubmitOrderResponce submitOrderResponce = response.body();
                    Toast.makeText(getContext(), "Successful" + submitOrderResponce.getOrderNo(), Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                    Constants.OrderNo = submitOrderResponce.getOrderNo();
                    Constants.VerId = submitOrderResponce.getOrderVersion();
                 /*   binding.llProduct.setVisibility(View.GONE);
                    binding.llReceiptVIew.setVisibility(View.VISIBLE);
                    RetriveData();*/

                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<SubmitOrderResponce> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), "Failed Submit", Toast.LENGTH_LONG).show();
            }
        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDraftProductAdded(DraftOrderModel draftOrderModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DraftProductModel> productsModels = orderDatabase.daoAccess().getAllDraftProducts();
            }
        }).start();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddProduct(OrderProductsModel productsModel) {
        for (OrderProductsModel productsModel1 : addedProducts) {
            if (productsModel1.getId() == productsModel.getId()) {
                double quantity = productsModel1.getQuantity() + productsModel.getQuantity();
                productsModel1.setQuantity(quantity);
                updateTotal();
                return;
            }
        }
        addedProducts.add(productsModel);
        updateTotal();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyUi(UiModificationEvent event) {
        showLayoutAddProductsBillPreview(event.isModifyUi());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddProductUpdate(MessageEvent event) {
        if (event.isUpdate())
            updateTotal();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProductUpdate(OrderProductsModel productsModel) {
        Productlist();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (orderDatabase != null)
            orderDatabase.close();
    }
}