package com.cse.oms.ui.OrderCollection;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.CustomerListRoomDb.CustomerListRoomDB;
import com.cse.oms.MyAdapters.CustomerListAdapter;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.CustomerResponse;
import com.cse.oms.R;
import com.cse.oms.databinding.OrderCollectionFragmentBinding;
import com.cse.oms.ui.CustomerList.CustomerListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderCollectionFragment extends Fragment implements CustomerListAdapter.UserClickListener {

    private OrderCollectionViewModel mViewModel;
    OrderCollectionFragmentBinding binding;
    RecyclerView CustomerList;
    List<CustomerListInfo> arrayList= new ArrayList<>();
    SearchView MySearch;


    public OrderCollectionFragment() {
    }


    public static OrderCollectionFragment newInstance() {
        return new OrderCollectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrderCollectionFragmentBinding.inflate(inflater);
        CustomerList = binding.myRecycleviews;
        MySearch = binding.searchview;


        //testing



        MySearch.clearFocus();
       // arrayList = new ArrayList<>();


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
        CustomerListAdapter adapter = new CustomerListAdapter(arrayList, requireContext(),this::selectedUser);
        CustomerList.setLayoutManager(new LinearLayoutManager(requireContext()));
        CustomerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


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


                    for (CustomerResponse post : nlist) {
                        try {
                            CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
                            CustomerListInfo customerListInfo = new CustomerListInfo(post.getTerritoryId(),
                                    post.getTerritoryName(), post.getSCId(), post.getDepotName(), post.getCustomerId(),
                                    post.getName(), post.getAddress());
                            db.customerListDAO().insertCustomerList(customerListInfo);
                        } catch (Exception e) {

                        }


                    }
                    loaddatainlistview();
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CustomerResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderCollectionViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void selectedUser(CustomerListInfo customerListInfo) {

        Toast.makeText(requireContext(),customerListInfo.getCustomerid()+" Selected",Toast.LENGTH_LONG).show();
    }
}