package com.cse.oms.ui.CustomerList;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.CustomerListRoomDb.CustomerListRoomDB;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.MyAdapters.CustomerListAdapter;
import com.cse.oms.MyAdapters.ProductListAdapter;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.CustomerResponse;
import com.cse.oms.ProductListRoomDb.ProductListRoomDB;
import com.cse.oms.R;
import com.cse.oms.databinding.CustomerListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerListFragment extends Fragment {

    private CustomerListViewModel mViewModel;
    CustomerListFragmentBinding binding;
    RecyclerView CustomerList;
    List<CustomerListInfo> arrayList;



    public static CustomerListFragment newInstance() {
        return new CustomerListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CustomerListFragmentBinding.inflate(inflater);
       CustomerList = binding.myRecycleview;


        arrayList = new ArrayList<>();









        Customerlist();
        loaddatainlistview();




        return binding.getRoot();
    }


    public void loaddatainlistview() {
        CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
        arrayList = db.customerListDAO().getAllCustomer();
        CustomerListAdapter adapter = new CustomerListAdapter(arrayList, requireContext());
        CustomerList.setLayoutManager(new LinearLayoutManager(requireContext()));
        CustomerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void Customerlist() {

        Intent intent = getActivity().getIntent();
        int tid = intent.getIntExtra("TerritoryId",0);
        int Scid = intent.getIntExtra("SalesLineId",0);
        Call<List<CustomerResponse>> call = ApiClient.getUserService().getAllCustomer(tid,Scid);
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
                                    post.getTerritoryName(),post.getSCId(),post.getDepotName(),post.getCustomerId(),
                                    post.getName(),post.getAddress());
                            db.customerListDAO().insertCustomerList(customerListInfo);
                        } catch (Exception e){
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

}