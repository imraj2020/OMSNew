package com.cse.oms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.oms.R;

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
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });


        ClickRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userid = EtUserId.getText().toString();
                password = EtPassword.getText().toString();

                if(userid.isEmpty()){
                    EtUserId.setError("User id cannot be empty");
                }
                if(password.isEmpty()){
                    EtPassword.setError("Password cannot be empty");
                }
                if(userid.equals("imraj") && password.equals("123")){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}