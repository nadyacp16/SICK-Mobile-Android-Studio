package com.example.acer.askatma.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiLogin;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.models.LoginResponse;
import com.example.acer.askatma.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class updatePassword extends AppCompatActivity {

    private TextView txtNewPass, txtRepeatPass, txtOldPass;
    private Button btnUpdatePass;
    private SharedPrefManager sharedPrefManager;
    private String newpass, repeatpass, idpeng, oldpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        sharedPrefManager = new SharedPrefManager(this);
        initWidget();
        idpeng = sharedPrefManager.getSpIdPel();
        onClickEdit(this);
    }

    public void updateUserPassword(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        newpass = txtNewPass.getText().toString().trim();
        oldpass = txtOldPass.getText().toString().trim();
        repeatpass = txtRepeatPass.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiLogin api = retrofit.create(ApiLogin.class);

        Call<LoginResponse> call = api.updateUserPassword(idpeng, newpass, oldpass);

        if(newpass.equals(repeatpass)) {
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                    progressDialog.dismiss();
                    Integer value = response.body().getValue();

                    if (value == 1) {
                        response.body();
                        finish();
                        Toast.makeText(updatePassword.this, "Success update password!", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(updatePassword.this, "Failed update password!", Toast.LENGTH_SHORT).show();
                }

            });
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(updatePassword.this, "Password don't match!", Toast.LENGTH_SHORT).show();
        }
    }

    public void initWidget(){
        txtNewPass = findViewById(R.id.newpassword);
        txtRepeatPass = findViewById(R.id.repeatpassword);
        txtOldPass = findViewById(R.id.oldpassword);
        btnUpdatePass = findViewById(R.id.btn_updatepassword);
    }

    void onClickEdit(updatePassword v) {
        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateUserPassword();
                } catch (Exception e) {
                }

            }
        });
    }
}
