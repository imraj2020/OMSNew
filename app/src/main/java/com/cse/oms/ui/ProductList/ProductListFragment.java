package com.cse.oms.ui.ProductList;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.MyAdapters.CustomerListAdapter;
import com.cse.oms.MyAdapters.ProductListAdapter;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.ProductResponse;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.ProductListRoomDb.ProductListRoomDB;
import com.cse.oms.R;
import com.cse.oms.databinding.ProductListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment {

    private ProductListViewModel mViewModel;
    ProductListFragmentBinding binding;
    RecyclerView ProductList;
    List<ProductListInfo> arrayList= new ArrayList<>();
    SearchView MySearch;
    TextView Tproduct;


    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProductListFragmentBinding.inflate(inflater);
        ProductList = binding.mylistview;
        MySearch = binding.productsearch;
        Tproduct = binding.totalproduct;

        SharedPreferences prefs = getActivity().getSharedPreferences("my_prefes", MODE_PRIVATE);

        int size = prefs.getInt("psize", 0);



        Tproduct.setText(Integer.toString(size));



        MySearch.clearFocus();

        MySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //testing
                ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
                arrayList = db.productListDAO().getAllProduct();
                ProductListAdapter adapter = new ProductListAdapter(arrayList, requireContext(),this::selectedUsers);
                ProductList.setLayoutManager(new LinearLayoutManager(requireContext()));
                ProductList.setAdapter(adapter);

                //end test
                adapter.getFilter().filter(newText);

                String searchstr = newText;
                return false;
            }

            private void selectedUsers(ProductListInfo productListInfo) {
                String Productname= productListInfo.getName();
                Toast.makeText(requireContext(),productListInfo.getName()+" Selected",Toast.LENGTH_LONG).show();
            }
        });






        Productlist();
        loaddatainlistview();
        return binding.getRoot();
    }

    public void loaddatainlistview() {
        ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
        arrayList = db.productListDAO().getAllProduct();
        ProductListAdapter adapter = new ProductListAdapter(arrayList, requireContext(),this::selectedUsers);
        ProductList.setLayoutManager(new LinearLayoutManager(requireContext()));
        ProductList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    public void Productlist() {

        Call<List<ProductResponse>> call = ApiClient.getUserService().getProduct();

        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                if (response.isSuccessful()) {

                    List<ProductResponse> nlist = response.body();


                    for (ProductResponse post : nlist) {

                        ProductListRoomDB db = ProductListRoomDB.getDbInstance(requireContext());
                        try {
                            ProductListInfo productListInfo = new ProductListInfo(post.getProductId(),
                                    post.getProductCode(),post.getName(),post.getDescription(),post.getTradePrice(),
                                    post.getProductStrength(),post.getPackSize(),post.getProductFamilyId(),post.getProductFamilyName(),
                                    post.getProductCategory(),post.getCOGS(),post.getMRP(),post.getVat(),post.getVatUnit(),
                                    post.getDiscounted(),post.getTPUnit(),post.getColdChangeProduct());
                            db.productListDAO().insertProduct(productListInfo);
                             // Toast.makeText(requireContext(),"Data inserted successfully",Toast.LENGTH_LONG).show();
                        } catch (Exception e){
//                              Toast.makeText(requireContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                        }

                    }
                    loaddatainlistview();
                } else {
                    Toast.makeText(getContext(), "Retrive Failed,please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Retrive Failed "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        // TODO: Use the ViewModel
    }


    public void selectedUsers(ProductListInfo productListInfo) {

        Toast.makeText(requireContext(),productListInfo.getName()+" Selected",Toast.LENGTH_LONG).show();
    }

}