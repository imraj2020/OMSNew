package com.cse.oms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ImageView ClickBack;
    TextView ClickLogin;
    EditText EtEmployeeCode, EtMobileNo, EtPassword, EtConfirmPassword;
    Button Register;
    String employeecode, mobileno, password, confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ClickBack = findViewById(R.id.clickback);
        ClickLogin = findViewById(R.id.clicklogin);
        EtEmployeeCode = findViewById(R.id.etEmployeeCode);
        EtMobileNo = findViewById(R.id.etMobileNo);
        EtPassword = findViewById(R.id.etPassword);
        EtConfirmPassword = findViewById(R.id.etConfirmPassword);
        Register = findViewById(R.id.cirRegisterButton);


        ClickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });


        ClickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                employeecode = EtEmployeeCode.getText().toString();
                mobileno = EtMobileNo.getText().toString();
                password = EtPassword.getText().toString();
                confirmpassword = EtConfirmPassword.getText().toString();


                if (employeecode.isEmpty()) {
                    EtEmployeeCode.setError("Employee code cannot be empty");
                }
                if (mobileno.isEmpty()) {
                    EtMobileNo.setError("Mobile no cannot be empty");
                }
                if (password.isEmpty()) {
                    EtPassword.setError("Password cannot be empty");
                }
                if (confirmpassword.isEmpty()) {
                    EtConfirmPassword.setError("Repeat Password cannot be empty");
                }

                if (!password.equals(confirmpassword)) {
                    Toast.makeText(RegisterActivity.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                    EtPassword.setError("Password Not matching");
                    EtConfirmPassword.setError("Password Not matching");
                }
                if(password.equals(confirmpassword)){
                    register();
                }


            }
        });


    }

    private void register() {
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        employeecode = EtEmployeeCode.getText().toString();
        mobileno = EtMobileNo.getText().toString();
        password = EtPassword.getText().toString();

        final Register register = new Register(employeecode,mobileno,password);

        Call<com.cse.oms.Network.Register> RegisterResponse = ApiClient.getUserService().REGISTER_CALL(register);
        RegisterResponse.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                    //Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_LONG).show();
                    Register registerresponse = response.body();
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Status is :" + registerresponse.getMessage(), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 700);

                } else {
                    Toast.makeText(getApplicationContext(), "Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}