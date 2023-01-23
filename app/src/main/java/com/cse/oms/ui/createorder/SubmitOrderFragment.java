package com.cse.oms.ui.createorder;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.cse.oms.CreateOrderRoomDatabase.database.OrderDatabase;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftProductModel;
import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.CustomerResponse;
import com.cse.oms.R;
import com.cse.oms.databinding.FragmentSubmitOrderBinding;
import com.cse.oms.ui.createorder.Adapter.AddedProductAdapter;
import com.cse.oms.ui.createorder.Adapter.POProductAdapter;
import com.cse.oms.ui.createorder.Adapter.SaleProductAdapter;
import com.cse.oms.ui.createorder.Utils.Constants;
import com.cse.oms.ui.createorder.Utils.Utilities;
import com.cse.oms.ui.createorder.model.OrderInfo.OrderBaicInfoResponse;
import com.cse.oms.ui.createorder.model.OrderInfo.OrderItem;
import com.cse.oms.ui.createorder.model.OrderProductsModel;
import com.cse.oms.ui.createorder.model.SubmitOrder;
import com.cse.oms.ui.createorder.model.SubmitOrderResponce;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmitOrderFragment extends Fragment {

    private static final String DRAFT_DIALOG = "draftDialog";
    //Customer List
    Dialog dialog;
    private final ArrayList<CustomerResponse> customers = new ArrayList<>();
    ArrayList<OrderProductsModel> productsModels = new ArrayList<>();
    ArrayList<OrderProductsModel> addedProducts = new ArrayList<>();
    SaleProductAdapter saleProductAdapter;
    AddedProductAdapter addedProductAdapter;
    int mYear, mMonth, mDay;
    String CustomerID, entryTime;
    Calendar calendar = Calendar.getInstance();
    FragmentActivity activity = getActivity();
    ArrayList<OrderItem> productList = new ArrayList<>();

    //Receipt Preview
    POProductAdapter productAdapter;
    private OrderDatabase orderDatabase;
    private FragmentSubmitOrderBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubmitOrderBinding.inflate(inflater);

        initRecyclerView();
        Productlist();
        SearchProduct();

        Customerlist();
        Onclick();
        initializeVariables();
        initDB();
        //Receipt Preview
        initReceiptRecyclerView();

        return binding.getRoot();
    }


    private void initDB() {
        try {
            orderDatabase = Room.databaseBuilder(getContext(),
                    OrderDatabase.class, Constants.DATABASE_NAME).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        binding.OrderDate.setText(formatter.format(calendar.getTime()));
        binding.DeliveryDate.setText(formatter.format(calendar.getTime()));
        entryTime = formatter.format(calendar.getTime());
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

    /*-------------------Customer List and Spinner----------------------*/
    public void Customerlist() {

        Intent intent = getActivity().getIntent();
        int tid = intent.getIntExtra("TerritoryId", 0);
        int Scid = intent.getIntExtra("SalesLineId", 0);
        Call<List<CustomerResponse>> call = ApiClient.getUserService().getAllCustomer(tid, Scid);
        call.enqueue(new Callback<List<CustomerResponse>>() {

            @Override
            public void onResponse(Call<List<CustomerResponse>> call, Response<List<CustomerResponse>> response) {

                if (response.isSuccessful()) {

                    List<CustomerResponse> nlist = response.body();
                    customers.addAll(response.body());
                    addSpinnerData(nlist);


                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<CustomerResponse>> call, Throwable t) {
                // Holidayres.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addSpinnerData(final List<CustomerResponse> body) {
        binding.tvCustomerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog = new Dialog(getContext());

                // set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                List<String> CustomerResponseList = new ArrayList<>();
                CustomerResponseList.add(0, "select");
                for (int i = 1; i < body.size(); i++) {
                    CustomerResponseList.add(i, body.get(i).getName() + "\n (" + body.get(i).getCustomerId() + ")\nAddress:" + body.get(i).getAddress() + "\n\n");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, CustomerResponseList);
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }


                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        binding.tvCustomerList.setText(adapter.getItem(position));

                        try {
                            // Toast.makeText(getContext(),binding.tvCustomerList.getText().toString(), Toast.LENGTH_SHORT).show();
                            CustomerID = getCustomerIdFromName(binding.tvCustomerList.getText().toString());
                            // Toast.makeText(getContext(),"CustomerID"+CustomerID, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Utilities.showLogcatMessage("binding.tvCustomerList " + e);

                        }
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public String getCustomerIdFromName(String name) {
        for (CustomerResponse customerModel : customers) {
            if ((customerModel.getName() + "\n (" + customerModel.getCustomerId() + ")\nAddress:" + customerModel.getAddress() + "\n\n").equalsIgnoreCase(name))
                return customerModel.getCustomerId();

        }
        return Constants.DUMMY_USER;
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

    public boolean SearchProduct() {
        // getting search view of our item.\

        // below line is to call set on query text listener method.
        binding.actionSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<OrderProductsModel> filteredlist = new ArrayList<OrderProductsModel>();

        // running a for loop to compare elements.
        for (OrderProductsModel item : productsModels) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            saleProductAdapter.filterList(filteredlist);
        }
    }

    private void saveDraftSale() {
        orderDatabase = Room.databaseBuilder(getContext(),
                OrderDatabase.class, Constants.DATABASE_NAME).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DraftOrderModel draftOrderModel = new DraftOrderModel();
                draftOrderModel.setDateTime(Utilities.getFormattedDateTime(Calendar.getInstance()));
                draftOrderModel.setOrderStatus(Constants.SALE_DRAFT_ORDER);
                draftOrderModel.setCustomerID(CustomerID);
                draftOrderModel.setCustomerName(binding.tvCustomerList.getText().toString());
                draftOrderModel.setOrderDate(binding.OrderDate.getText().toString());
                draftOrderModel.setDeliveryDate(binding.DeliveryDate.getText().toString());
                draftOrderModel.setNote(binding.Note.getText().toString());
                draftOrderModel.setEntryTime(entryTime);
                draftOrderModel.setAmount(Double.parseDouble(binding.etNetPayment.getText().toString()));
                long orderID = orderDatabase.daoAccess().insertOrder(draftOrderModel);

                for (int i = 0; i < addedProducts.size(); i++) {
                    DraftProductModel draftProductModel = new DraftProductModel();
                    draftProductModel.setProductId(addedProducts.get(i).getProductId());
                    draftProductModel.setName(addedProducts.get(i).getName());
                    draftProductModel.setOrderId((int) orderID);
                    draftProductModel.setAmount(addedProducts.get(i).getAmount());
                    draftProductModel.setQuantity(addedProducts.get(i).getQuantity());
                    draftProductModel.setUnitPrice(addedProducts.get(i).getTradePrice());
                    draftProductModel.setStatus(0);
                    orderDatabase.daoAccess().insertProduct(draftProductModel);
                }
                getActivity().runOnUiThread(new Runnable() {

                    public void run() {
                        Toast.makeText(getContext(), "Save to Draft", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();
    }


    public void Onclick() {
        binding.btnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDraftSale();
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBtnOnClick();
            }
        });


        binding.DeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int
                                    selectedYear, int selectedMonth, int selectedDay) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, selectedYear);
                                calendar.set(Calendar.MONTH, selectedMonth);
                                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                binding.DeliveryDate.setText(formatter.format(calendar.getTime()));
                            }
                        }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });


        binding.OrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int
                                    selectedYear, int selectedMonth, int selectedDay) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, selectedYear);
                                calendar.set(Calendar.MONTH, selectedMonth);
                                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                binding.OrderDate.setText(formatter.format(calendar.getTime()));
                            }
                        }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        binding.NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.llCustomerDetails.setVisibility(View.GONE);
                binding.llProduct.setVisibility(View.VISIBLE);
            }
        });

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
        binding.PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.llCustomerDetails.setVisibility(View.VISIBLE);
                binding.llProduct.setVisibility(View.GONE);
            }
        });

    }


    public void submitBtnOnClick() {
        //showProgressBar(true);
        SubmitOrder submitOrder = new SubmitOrder();
        submitOrder.setOrderDetails(addedProducts);
        submitOrder.setCustomerId(CustomerID);
        submitOrder.setOrderDate(binding.OrderDate.getText().toString());
        submitOrder.setDeliveryDate(binding.DeliveryDate.getText().toString());
        submitOrder.setEntryDateTime(entryTime);

        LoginResRoomDB database = LoginResRoomDB.getDbInstance(requireContext());
        List<LoginResInfo> list = database.loginResDAO().getAllData();
        int empid = list.get(0).getEmpId();
        int saleslineid = list.get(0).getSalesLineId();


        submitOrder.setEntryBy(empid);
        submitOrder.setNote(binding.Note.getText().toString());
        submitOrder.setTerritoryId(Constants.TerritoryId);
        submitOrder.setSCId(String.valueOf(saleslineid));
        submitOrder.setOnBehalfOfEmpId(Constants.OnBehalfOfEmpId);
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        Gson g = new Gson();
        String str = g.toJson(submitOrder);
        Utilities.showLogcatMessage("DATA" + str);
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
                    binding.llProduct.setVisibility(View.GONE);
                    binding.llReceiptVIew.setVisibility(View.VISIBLE);
                    RetriveData();

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

    //update product price
    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < addedProducts.size(); i++) {
            total += addedProducts.get(i).getAmount();
        }
        binding.etNetPayment.setText(String.format("%.2f", total));
        addedProductAdapter.notifyDataSetChanged();
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
//Receipt Preview

    private void initReceiptRecyclerView() {
        try {
            productAdapter = new POProductAdapter(productList, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.rvProductbills.setLayoutManager(layoutManager);
            binding.rvProductbills.setAdapter(productAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RetriveData() {

        //  Intent intent = getActivity().getIntent();
        String OrderNo = Constants.OrderNo;
        int VerID = Constants.VerId;
        Call<OrderBaicInfoResponse> call = ApiClient.getUserService().GetPoInfo(OrderNo, VerID);
        call.enqueue(new Callback<OrderBaicInfoResponse>() {

            @Override
            public void onResponse(Call<OrderBaicInfoResponse> call, Response<OrderBaicInfoResponse> response) {

                if (response.isSuccessful()) {
                    binding.OrderNo.setText(response.body().getOrderBaicInfo().getOrderNo());
                    binding.tvOrderDate.setText(response.body().getOrderBaicInfo().getOrderDate());
                    binding.DelivaryDate.setText(response.body().getOrderBaicInfo().getDeliveryDate());
                    binding.CustomerName.setText(response.body().getOrderBaicInfo().getCustomerName());
                    binding.CustomerAddrss.setText(response.body().getOrderBaicInfo().getCustomerAddress());
                    binding.TotalAmount.setText(String.format("%.2f", response.body().getOrderBaicInfo().getTotalOrderPrice()));
                    productList.clear();
                    productList.addAll(response.body().getOrderItemList());
                    productAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<OrderBaicInfoResponse> call, Throwable t) {
                // Holidayres.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Event Subscription
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setDrafts(final DraftOrderModel draftOrderModel) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DraftProductModel> draftProductModels = orderDatabase.daoAccess().getProductsByOrder(draftOrderModel.getId());
                addedProducts.clear();
                for (DraftProductModel draftProductModel : draftProductModels) {
                    OrderProductsModel productsModel = new OrderProductsModel();
                    productsModel.setProductId(draftProductModel.getId());
                    productsModel.setName(draftProductModel.getName());
                    productsModel.setQuantity(draftProductModel.getQuantity());
                    productsModel.setAmount(draftProductModel.getAmount());
                    addedProducts.add(productsModel);
                }


                if (!addedProducts.isEmpty()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showLayoutAddProductsBillPreview(true);
                            addedProductAdapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddProduct(OrderProductsModel productsModel) {
        for (OrderProductsModel productsModel1 : addedProducts) {
            if (productsModel1.getProductId() == productsModel.getProductId()) {
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
        // Productlist();

    }

   /* @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDraftProductAdded(DraftOrderModel draftOrderModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DraftProductModel> productsModels = orderDatabase.daoAccess().getAllDraftProducts();
            }
        }).start();

    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        orderDatabase.close();
    }
}
