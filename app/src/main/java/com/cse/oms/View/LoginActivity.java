package com.cse.oms.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.LoginResponse;
import com.cse.oms.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    ImageView ClickPlus;
    TextView ClickRegister;
    EditText EtUserId, EtPassword;
    String userid, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.cirLoginButton);
        ClickPlus = findViewById(R.id.clickplus);
        ClickRegister = findViewById(R.id.clickregister);
        EtUserId = findViewById(R.id.etUserId);
        EtPassword = findViewById(R.id.etPassWords);


        ClickPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });


        ClickRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userid = EtUserId.getText().toString();
                password = EtPassword.getText().toString();

                if (userid.isEmpty()) {
                    EtUserId.setError("User id cannot be empty");
                }
                if (password.isEmpty()) {
                    EtPassword.setError("Password cannot be empty");
                }

                login();
//                if(userid.equals("imraj") && password.equals("123")){
//                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
            }
        });


    }

    public void login() {

        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(userid, password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    int EmpId, BUId,SalesLineId, RegionId, TeamId, TerritoryId;
                    String FullName, EmpNetworkId, EmpCode, Email, MobileNo, DepartmentName, DesignationName, BUName,
                            SalesLineName, RegionName, TeamName, TerritoryName;

                    EmpId = loginResponse.getEmpId();
                    FullName = loginResponse.getFullName();
                    EmpNetworkId = loginResponse.getEmpNetworkId();
                    EmpCode = loginResponse.getEmpCode();
                    Email = loginResponse.getEmail();
                    MobileNo = loginResponse.getMobileNo();
                    DepartmentName = loginResponse.getDepartmentName();
                    DesignationName = loginResponse.getDesignationName();
                    BUId = loginResponse.getBUId();
                    BUName = loginResponse.getBUName();
                    SalesLineId = loginResponse.getSalesLineId();
                    SalesLineName = loginResponse.getSalesLineName();
                    RegionId = loginResponse.getRegionId();
                    RegionName = loginResponse.getRegionName();
                    TeamId = loginResponse.getTeamId();
                    TeamName = loginResponse.getTeamName();
                    TerritoryId = loginResponse.getTerritoryId();
                    TerritoryName = loginResponse.getTerritoryName();

                    LoginResRoomDB db = LoginResRoomDB.getDbInstance(getApplicationContext());

                    try {
                        LoginResInfo loginResInfo = new LoginResInfo(EmpId,FullName,EmpNetworkId,EmpCode,Email,MobileNo,DepartmentName,
                                DesignationName,BUId,BUName,SalesLineId,SalesLineName,RegionId,RegionName,TeamId,TeamName,
                                TerritoryId,TerritoryName);
                        db.loginResDAO().insertLoginResponse(loginResInfo);
                        mProgressDialog.dismiss();
                    } catch (Exception e){
                      //  Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }




                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("EmpId",EmpId);
                    i.putExtra("TerritoryId",TerritoryId);
                    startActivity(i);


                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();


            }

            //

        });

    }
}