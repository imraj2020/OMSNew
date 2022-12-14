package com.cse.oms.ui.CustomerList;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.CustomerListRoomDb.CustomerListRoomDB;
import com.cse.oms.MyAdapters.CustomerListAdapter;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.CustomerResponse;
import com.cse.oms.databinding.CustomerListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerListFragment extends Fragment implements CustomerListAdapter.UserClickListener {

    private CustomerListViewModel mViewModel;
    CustomerListFragmentBinding binding;
    RecyclerView CustomerList;
    List<CustomerListInfo> arrayList= new ArrayList<>();
    SearchView MySearch;
    TextView TCustomer;


    public static CustomerListFragment newInstance() {
        return new CustomerListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CustomerListFragmentBinding.inflate(inflater);
        CustomerList = binding.myRecycleview;

        MySearch = binding.searchviews;
        TCustomer = binding.totalcustomer;

        SharedPreferences prefs = getActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);

        int size = prefs.getInt("size", 0);



        TCustomer.setText(Integer.toString(size));


        MySearch.clearFocus();

        MySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //testing
                CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
                arrayList = db.customerListDAO().getAllCustomer();
                CustomerListAdapter adapter = new CustomerListAdapter(arrayList, requireContext(),this::selectedUser);
                CustomerList.setLayoutManager(new LinearLayoutManager(requireContext()));
                CustomerList.setAdapter(adapter);

                //end test
                adapter.getFilter().filter(newText);

                String searchstr = newText;
                return false;
            }

            private void selectedUser(CustomerListInfo customerListInfo) {
                String Customerid= customerListInfo.getCustomerid();
                Toast.makeText(requireContext(),customerListInfo.getCustomerid()+" Selected",Toast.LENGTH_LONG).show();
            }
        });


        Customerlist();
        loaddatainlistview();


        return binding.getRoot();
    }


    public void loaddatainlistview() {
        CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
        arrayList = db.customerListDAO().getAllCustomer();
        CustomerListAdapter adapter = new CustomerListAdapter(arrayList, requireContext(), this::selectedUser);
        CustomerList.setLayoutManager(new LinearLayoutManager(requireContext()));
        CustomerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void Customerlist() {

        Intent intent = getActivity().getIntent();
        int tid = intent.getIntExtra("TerritoryId", 0);
        int Scid = intent.getIntExtra("SalesLineId", 0);
        Call<List<CustomerResponse>> call = ApiClient.getUserService().getAllCustomer(tid, Scid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<CustomerResponse>>() {
            @Override
            public void onResponse(Call<List<CustomerResponse>> call, Response<List<CustomerResponse>> response) {

                if (response.isSuccessful()) {

                    List<CustomerResponse> nlist = response.body();


                    for (CustomerResponse post : nlist) {
                        try {
                            CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
                            CustomerListInfo customerListInfo = new CustomerListInfo(post.getTerritoryId(),
                                    post.getTerritoryName(), post.getSCId(), post.getDepotName(), post.getCustomerId(),
                                    post.getName(), post.getAddress());
                            db.customerListDAO().insertCustomerList(customerListInfo);
                        } catch (Exception e) {
//                            Toast.makeText(requireContext(),"Data Already Exist",Toast.LENGTH_LONG).show();
                        }


                    }
                    loaddatainlistview();
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void selectedUser(CustomerListInfo customerListInfo) {

        Toast.makeText(requireContext(),customerListInfo.getCustomerid()+" Selected",Toast.LENGTH_LONG).show();
    }
}