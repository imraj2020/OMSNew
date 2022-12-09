package com.cse.oms.ui.DataSync;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.CustomerListRoomDb.CustomerListRoomDB;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.CustomerResponse;
import com.cse.oms.Network.ProductResponse;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.ProductListRoomDb.ProductListRoomDB;
import com.cse.oms.R;
import com.cse.oms.databinding.DataSyncFragmentBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSyncFragment extends Fragment {

    private DataSyncViewModel mViewModel;
    DataSyncFragmentBinding binding;
    Button ProductUpdate, CustomerUpdate;

    public static DataSyncFragment newInstance() {
        return new DataSyncFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataSyncFragmentBinding.inflate(inflater);
        ProductUpdate = binding.productupdate;
        CustomerUpdate = binding.customerupdate;

        ProductUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
                db.productListDAO().deleteAll();
                Productlist();
                Toast.makeText(requireContext(),"Product Update Complete",Toast.LENGTH_LONG).show();
            }
        });

        CustomerUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerListRoomDB db = CustomerListRoomDB.getDbInstance(requireContext());
                db.customerListDAO().deleteAlls();
                Customerlist();
                Toast.makeText(requireContext(),"Customer Update Complete",Toast.LENGTH_LONG).show();
            }
        });





        return binding.getRoot();
    }


    public void Productlist() {

        Call<List<ProductResponse>> call = ApiClient.getUserService().getProduct();

        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                if (response.isSuccessful()) {

                    List<ProductResponse> nlist = response.body();
                    Toast.makeText(getContext(), "Response successfull"+nlist, Toast.LENGTH_SHORT).show();


                    for (ProductResponse post : nlist) {

                        ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
//                        try {
//                            ProductListInfo productListInfo = new ProductListInfo(post.getProductId(),
//                                    post.getProductCode(),post.getName(),post.getDescription(),post.getTradePrice(),
//                                    post.getProductStrength(),post.getPackSize(),post.getProductFamilyId(),post.getProductFamilyName(),
//                                    post.getProductCategory(),post.getCOGS(),post.getMRP(),post.getVat(),post.getVatUnit(),
//                                    post.getDiscounted(),post.getTPUnit(),post.getColdChangeProduct());
//                            db.productListDAO().insertProduct(productListInfo);
//                            // Toast.makeText(requireContext(),"Data inserted successfully",Toast.LENGTH_LONG).show();
//                        } catch (Exception e){
//                            //  Toast.makeText(requireContext(),"Error: "+e,Toast.LENGTH_LONG).show();
//                        }

                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                // Holidayres.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
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
        mViewModel = new ViewModelProvider(this).get(DataSyncViewModel.class);
        // TODO: Use the ViewModel
    }

}