package com.cse.oms.ui.OrderStatus;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.oms.MyAdapters.OrderStatusAdapter;
import com.cse.oms.MyAdapters.ProductListAdapter;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.OrderStatus;
import com.cse.oms.Network.Register;
import com.cse.oms.ProductListRoomDb.ProductListRoomDB;
import com.cse.oms.R;
import com.cse.oms.View.LoginDbHelper;
import com.cse.oms.databinding.OrderStatusFragmentBinding;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusFragment extends Fragment {

    private OrderStatusViewModel mViewModel;
    OrderStatusFragmentBinding binding;
    EditText OrderDate, DeliveryDate, CustomerId;
    LoginDbHelper loginDbHelper;
    public static String username;
    DatePickerDialog datePickerDialog;
    Button SearchButton, BackButton;
    String orderdate, deliverydate, customerid;
    ScrollView OrderScrollView;
    TextView TotalResult;
    RecyclerView OrderDetail;
    public static List<OrderStatus> nlist;

    public static OrderStatusFragment newInstance() {
        return new OrderStatusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrderStatusFragmentBinding.inflate(inflater);

        OrderDate = binding.etorderdate;
        DeliveryDate = binding.etdeliverydate;
        CustomerId = binding.etcustomerid;
        SearchButton = binding.SearchButton;
        BackButton = binding.goback;
        OrderScrollView = binding.orderscroll;
        OrderDetail = binding.orderstatuslist;
//        TotalResult = binding.totalresult;
//
//        SharedPreferences prefs = getActivity().getSharedPreferences("my_prefes", MODE_PRIVATE);
//
//        int size = prefs.getInt("totalR", 0);
//
//
//
//        TotalResult.setText(Integer.toString(size));


        // get the username
        loginDbHelper = new LoginDbHelper(requireContext());

        Cursor cursor = loginDbHelper.getNameData();

        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
                //Toast.makeText(requireContext(), "Username is:"+username, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }


        OrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                OrderDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        DeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                DeliveryDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                ;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();


            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Move one Fragment to another
//                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//                ft.replace(R.id.nav_host_fragment_content_main, new OrderStatusFragment(), null);
//                ft.addToBackStack(OrderStatusFragment.class.getName()); // you can use a string here, using the class name is just convenient
//                ft.commit();


                binding.orderstatussrch.setVisibility(View.VISIBLE);
                binding.orderstatusresult.setVisibility(View.GONE);
            }
        });


        return binding.getRoot();
    }






    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderStatusViewModel.class);
        // TODO: Use the ViewModel
    }


    public void register() {
        orderdate = OrderDate.getText().toString();
        deliverydate = DeliveryDate.getText().toString();
        customerid = CustomerId.getText().toString();

        Call<List<OrderStatus>> OrderStatusResponse = ApiClient.getUserService().ORDER_STATUS_CALL(username,orderdate,deliverydate,customerid);
        OrderStatusResponse.enqueue(new Callback<List<OrderStatus>>() {
            @Override
            public void onResponse(Call<List<OrderStatus>> call, Response<List<OrderStatus>> response) {

                if (response.isSuccessful()) {

                    nlist = response.body();
                    //public static List<NewOrderStatus> = nlist;
                    binding.orderstatussrch.setVisibility(View.GONE);
                    binding.orderstatusresult.setVisibility(View.VISIBLE);

                    int count= nlist.size();

                    binding.totalresult.setText(Integer.toString(count));
//                    if(nlist.isEmpty()){
//                        Toast.makeText(requireContext(), " No Data Found ! ", Toast.LENGTH_LONG).show();
//                    }
//
//                    for (OrderStatus post : nlist) {
//                        String content = "";
//                        content += "Order No: " + post.getOrderNo() + "\n";
//                        content += "Customer Id: " + post.getCustomerId()+ "\n";
//                        content += "Order Date: " + post.getOrderDate()+ "\n";
//                        content += "Delivery Date: " + post.getDeliveryDate()+ "\n";
//                        content += "Note : " + post.getNote() + "\n";
//                        content += "Territory Id: " + post.getTerritoryId()+ "\n";
//                        content += "SCId: " + post.getSCId()+ "\n";
//                        content += "Order Status: " + post.getOrderStatus()+ "\n\n";
//
//
//
//                        OrderDetails.append(content);
//                        // Holidayres.append(content);
//                    }



                    loaddatainlistview();


                } else {
                    Toast.makeText(requireContext(), "Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            public void loaddatainlistview() {
//                ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
//                arrayList = db.productListDAO().getAllProduct();
                OrderStatusAdapter adapter = new OrderStatusAdapter(nlist, requireContext());
                OrderDetail.setLayoutManager(new LinearLayoutManager(requireContext()));
                OrderDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderStatus>> call, Throwable t) {
//                Toast.makeText(requireContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(requireContext(), "No Data Found !!! ", Toast.LENGTH_LONG).show();
            }
        });

    }


}