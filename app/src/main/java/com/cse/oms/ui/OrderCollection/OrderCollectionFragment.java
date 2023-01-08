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

public class OrderCollectionFragment extends Fragment {

    private OrderCollectionViewModel mViewModel;
    OrderCollectionFragmentBinding binding;
    RecyclerView CustomerList;
    List<CustomerListInfo> arrayList= new ArrayList<>();
    SearchView MySearch;


    public static OrderCollectionFragment newInstance() {
        return new OrderCollectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrderCollectionFragmentBinding.inflate(inflater);















        return binding.getRoot();
    }







    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderCollectionViewModel.class);
        // TODO: Use the ViewModel
    }


}