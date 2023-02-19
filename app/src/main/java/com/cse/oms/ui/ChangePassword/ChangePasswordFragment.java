package com.cse.oms.ui.ChangePassword;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.ChengePasswordResponse;
import com.cse.oms.R;
import com.cse.oms.View.LoginActivity;
import com.cse.oms.View.MainActivity;
import com.cse.oms.databinding.FragmentChangePasswordBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends Fragment {

    private ChangePasswordViewModel mViewModel;
    FragmentChangePasswordBinding binding;
    EditText Username,OldPassword,NewPassword,ConfirmPassword;
    Button Savebutton;


    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater);
        Username = binding.etusername;
        OldPassword = binding.etoldpassword;
        NewPassword = binding.etnewpassword;
        ConfirmPassword = binding.etconfirmpassword;
        Savebutton = binding.savebtn;


        Savebutton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(Username.getText().toString()) || TextUtils.isEmpty(OldPassword.getText().toString()) ||
                    TextUtils.isEmpty(NewPassword.getText().toString()) || TextUtils.isEmpty(ConfirmPassword.getText().toString())){
                Toast.makeText(requireContext(),"All Field is Required", Toast.LENGTH_LONG).show();
            }else{

                if (NewPassword.getText().toString().equals(ConfirmPassword.getText().toString())){

                    chengepassword();

                }else{
                    ConfirmPassword.setError("Password Did Not Matched");
                }
            }
        });



        return binding.getRoot();
    }

    private void chengepassword() {
        String username = Username.getText().toString();
        String oldpassword = OldPassword.getText().toString();
        String newpassword = NewPassword.getText().toString();



        Call<ChengePasswordResponse> loginResponseCall = ApiClient.getUserService().chengePassword(username,oldpassword,newpassword);
        loginResponseCall.enqueue(new Callback<ChengePasswordResponse>() {
            @Override
            public void onResponse(Call<ChengePasswordResponse> call, Response<ChengePasswordResponse> response) {

                if(response.isSuccessful()){
                    //Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_LONG).show();
                    ChengePasswordResponse chengePasswordResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :"+chengePasswordResponse.getMessage(), Toast.LENGTH_LONG).show();

                    if(chengePasswordResponse.isSuccess() == true){

                        Toast.makeText(requireContext(), "Please Login Again with new password ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },700);

                }else{
                    Toast.makeText(requireContext(),"Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ChengePasswordResponse> call, Throwable t) {
                Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);
        // TODO: Use the ViewModel
    }

}