package com.example.acer.askatma.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryDetailAdmin extends AppCompatActivity {

    private TextView txtTgl, txtTujuan, txtIsi, txtStatus, txtKomentar, txtFeedback, txtUser, txtTPKP, textpengirim, texttpkp;
    private Button btnproseskeluhan;
    private String temp, tempidperan;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_history_detail_admin);
        sharedPrefManager = new SharedPrefManager(this);
        initKeluhan();
        tempidperan = sharedPrefManager.getSpidPeran();
        temp = extras.getString("kode_keluhan");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        Call<ListKeluhan> call = api.showComplaintByIdKeluhan(temp);

        call.enqueue(new Callback<ListKeluhan>() {
            @Override
            public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                Integer value=response.body().getValue();

                if(value==1) {
                    response.body();
                    txtTgl.setText(response.body().getResult().get(0).getTgl_pengaduan());
                    txtTujuan.setText(response.body().getResult().get(0).getId_kategori());
                    txtIsi.setText(response.body().getResult().get(0).getIsi());
                    txtStatus.setText(response.body().getResult().get(0).getStatus());
                    txtKomentar.setText(response.body().getResult().get(0).getKomentar());
                    txtFeedback.setText(response.body().getResult().get(0).getFeedback());

                    if(!tempidperan.equals("1")) {
                        txtUser.setVisibility(View.INVISIBLE);
                        txtTPKP.setVisibility(View.INVISIBLE);

                        textpengirim.setVisibility(View.INVISIBLE);
                        texttpkp.setVisibility(View.INVISIBLE);
                    }
                    else{
                        txtUser.setText(response.body().getResult().get(0).getUser());
                        txtTPKP.setText(response.body().getResult().get(0).getNamatpkp());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {

            }
        });
    }

    private void initKeluhan(){
        txtIsi = findViewById(R.id.frgdeskripsikeluhanactadmin);
        txtTgl = findViewById(R.id.frgtglkeluhanactadmin);
        txtStatus = findViewById(R.id.frgisistatusactadmin);
        txtTujuan = findViewById(R.id.frgtujuankeluhanactadmin);
        txtKomentar = findViewById(R.id.frgkomentarkeluhanactadmin);
        txtFeedback = findViewById(R.id.frgfeedbackactadmin);
        txtUser = findViewById(R.id.frgpengirimactadmin);
        txtTPKP = findViewById(R.id.frgtpkpactadmin);

        textpengirim = findViewById(R.id.txtpengirim);
        texttpkp = findViewById(R.id.txttpkp);

        btnproseskeluhan = findViewById(R.id.btnproseskeluhanadmin);
    }
}
