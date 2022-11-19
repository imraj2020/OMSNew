package com.cse.oms.ui.OrderCollection;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.R;
import com.cse.oms.databinding.OrderCollectionFragmentBinding;
import com.cse.oms.ui.CustomerList.CustomerListFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderCollectionFragment extends Fragment {

    private OrderCollectionViewModel mViewModel;
    OrderCollectionFragmentBinding binding;
    RecyclerView CustomerList;
    List<CustomerListInfo> arrayList;

    public static OrderCollectionFragment newInstance() {
        return new OrderCollectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrderCollectionFragmentBinding.inflate(inflater);
        CustomerList = binding.myRecycleviews;
        arrayList = new ArrayList<>();


//        CustomerListFragment fragment = (CustomerListFragment) getFragmentManager().findFragmentById(R.id.nav_customerlist);
//        fragment.Customerlist();
//        fragment.loaddatainlistview();
//        Customerlist();
//        loaddatainlistview();



        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderCollectionViewModel.class);
        // TODO: Use the ViewModel
    }

}