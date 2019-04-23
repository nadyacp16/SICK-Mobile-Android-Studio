package com.example.acer.askatma.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.askatma.Api.Api;
import com.example.acer.askatma.Api.ApiLogin;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.models.LoginResponse;
import com.example.acer.askatma.models.User;
import com.example.acer.askatma.storage.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUsername, txtPassword;
    public String username, password, passuser;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progress;
    private boolean isUserClickedBackButton=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager=new SharedPrefManager(this);

        initWidget();

        onClickLogin(this);
    }

    private void initWidget()
    {
        btnLogin = (Button)findViewById(R.id.btn_login);
        txtUsername = (EditText)findViewById(R.id.username);
        txtPassword = (EditText)findViewById(R.id.password);
    }

    private void userSignIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        username = txtUsername.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiLogin api = retrofit.create(ApiLogin.class);

        Call<LoginResponse> call = api.userLogin(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                Integer value=response.body().getValue();
                progressDialog.dismiss();

                if(value==1){
                    response.body();
                        finish();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent homeUser=new Intent(MainActivity.this,nav.class);
                        Intent homeAdmin=new Intent(MainActivity.this,NavAdmin.class);
                        if(response.body().getResult().get(0).getIdperan() != 7){
                            homeAdmin.putExtra("nomoridentitas", response.body().getResult().get(0).getId());
                            homeAdmin.putExtra("id_peran", String.valueOf(response.body().getResult().get(0).getIdperan()));
                            sharedPrefManager.saveSPString(SharedPrefManager.KEY_USER_ID, response.body().getResult().get(0).getId());
                            sharedPrefManager.saveSPString(SharedPrefManager.KEY_ID_PERAN, String.valueOf(response.body().getResult().get(0).getIdperan()));
                            startActivity(homeAdmin);
                        }
                        else{
                            homeUser.putExtra("nomoridentitas", response.body().getResult().get(0).getId());
                            sharedPrefManager.saveSPString(SharedPrefManager.KEY_USER_ID, response.body().getResult().get(0).getId());
                            startActivity(homeUser);
                        }
                }else{
                    Toast.makeText(MainActivity.this, "Login failed please check username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBackPressed(){
        if(!isUserClickedBackButton){
            Toast.makeText(this,"Press back again to exit",Toast.LENGTH_LONG).show();
            isUserClickedBackButton=true;
        }else{
            super.onBackPressed();
            System.exit(1);
            finish();
        }
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                isUserClickedBackButton=false;
            }
        }.start();
    }

    void onClickLogin(MainActivity v) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    username = txtUsername.getText().toString();
                    password = txtPassword.getText().toString();
                    userSignIn();
                } catch (Exception e) {
                }

            }
        });
    }
}