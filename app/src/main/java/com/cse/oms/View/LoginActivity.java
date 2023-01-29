package com.cse.oms.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.Network.ApiClient;
import com.cse.oms.Network.LoginResponse;
import com.cse.oms.R;
import com.cse.oms.ui.createorder.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    ImageView ClickPlus;
    TextView ClickRegister;
    EditText EtUserId, EtPassword;
    String userid, password;
    LoginDbHelper dbs;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.cirLoginButton);
        ClickPlus = findViewById(R.id.clickplus);
        ClickRegister = findViewById(R.id.clickregister);
        EtUserId = findViewById(R.id.etUserId);
        EtPassword = findViewById(R.id.etPassWords);
        dbs = new LoginDbHelper(this);


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
                savePreferences();
                login();
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
                    //database cursor for store login
                    dbs = new LoginDbHelper(getApplicationContext());
                    Cursor cursor = dbs.alldata();
                    if (cursor.getCount() == 0) {
                        LoginDbHelper LoginDbHelper = new LoginDbHelper(getApplicationContext());
                        LoginDbHelper.insertRecord(userid, password);
                    } else {
                        //  Toast.makeText(getApplicationContext(), "Data Already Exist", Toast.LENGTH_SHORT).show();
                    }
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

                    Constants.TerritoryId = loginResponse.getTerritoryId();
                    Constants.OnBehalfOfEmpId = loginResponse.getEmpId();
                    Constants.EmpCode = loginResponse.getEmpCode();

                    LoginResRoomDB db = LoginResRoomDB.getDbInstance(getApplicationContext());

                    try {
                        LoginResInfo loginResInfo = new LoginResInfo(EmpId, FullName, EmpNetworkId, EmpCode, Email, MobileNo, DepartmentName,
                                DesignationName, BUId, BUName, SalesLineId, SalesLineName, RegionId, RegionName, TeamId, TeamName,
                                TerritoryId, TerritoryName);
                        db.loginResDAO().insertLoginResponse(loginResInfo);
                        mProgressDialog.dismiss();
                    } catch (Exception e) {
                      //  Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }




                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("EmpId",EmpId);
                    i.putExtra("TerritoryId",TerritoryId);
                    i.putExtra("SalesLineId",SalesLineId);
                    startActivity(i);
                    LoginActivity.this.finish();


                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mProgressDialog.dismiss();

                userid = EtUserId.getText().toString();
                password = EtPassword.getText().toString();

                if(userid.equals("")||password.equals("")){
                    mProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = dbs.checkusernamepassword(userid, password);
                    if (checkuserpass == true) {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       // intent.putExtra("Employee", userid);
                        startActivity(intent);
                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UnameValue = String.valueOf(EtUserId.getText());
        PasswordValue = String.valueOf(EtPassword.getText());
        System.out.println("onPause save name: " + UnameValue);
        System.out.println("onPause save password: " + PasswordValue);
        editor.putString(PREF_UNAME, UnameValue);
        editor.putString(PREF_PASSWORD, PasswordValue);
        editor.commit();
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        EtUserId.setText(UnameValue);
//        EtPassword.setText(PasswordValue);
//        System.out.println("onResume load name: " + UnameValue);
//        System.out.println("onResume load password: " + PasswordValue);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}