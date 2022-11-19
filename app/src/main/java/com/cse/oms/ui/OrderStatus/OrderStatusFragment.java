package com.cse.oms.ui.OrderStatus;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.oms.R;
import com.cse.oms.databinding.OrderStatusFragmentBinding;

public class OrderStatusFragment extends Fragment {

    private OrderStatusViewModel mViewModel;
    OrderStatusFragmentBinding binding;

    public static OrderStatusFragment newInstance() {
        return new OrderStatusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrderStatusFragmentBinding.inflate(inflater);



        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderStatusViewModel.class);
        // TODO: Use the ViewModel
    }

}