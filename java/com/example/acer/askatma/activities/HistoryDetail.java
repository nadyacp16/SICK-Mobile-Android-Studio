package com.example.acer.askatma.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.storage.SharedPrefManager;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryDetail extends AppCompatActivity {

    private TextView txtTgl, txtTujuan, txtIsi, txtStatus, txtKomentar, txtFeedback, txtgivefeedback;
    private Button btnLike, btnDislike;
    private CardView cardFeedback;
    private String temp;
    private SharedPrefManager sharedPrefManager;
    private String text = "Thank you for your feedback!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_history_detail);
        sharedPrefManager = new SharedPrefManager(this);
        initKeluhan();

        btnLike.setVisibility(View.VISIBLE);
        btnDislike.setVisibility(View.VISIBLE);

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

                    if(txtStatus.getText().toString().equals("Selesai")) {
                        if (txtFeedback.getText().toString().equals("Good")) {
                            btnDislike.setVisibility(View.INVISIBLE);
                            btnLike.setClickable(false);
                        } else if (txtFeedback.getText().toString().equals("Bad")) {
                            btnLike.setVisibility(View.INVISIBLE);
                            btnDislike.setClickable(false);
                        } else if (txtFeedback.getText().toString().equals("")) {
                            btnLike.setVisibility(View.VISIBLE);
                            btnDislike.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        if (txtFeedback.getText().toString().equals("Good")) {
                            btnDislike.setVisibility(View.INVISIBLE);
                            btnLike.setClickable(false);
                            txtgivefeedback.setText(text);
                        } else if (txtFeedback.getText().toString().equals("Bad")) {
                            btnLike.setVisibility(View.INVISIBLE);
                            btnDislike.setClickable(false);
                            txtgivefeedback.setText(text);
                        } else if (txtFeedback.getText().toString().equals("")) {
                            btnLike.setVisibility(View.INVISIBLE);
                            btnDislike.setVisibility(View.INVISIBLE);
                            cardFeedback.setVisibility(View.INVISIBLE);
                            txtgivefeedback.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {

            }
        });

        onClickLike(this);
        onClickDislike(this);
    }

    public void feedbackLike(String feedback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        Call<ListKeluhan> call = api.updateUserFeedback(temp, feedback);

        call.enqueue(new Callback<ListKeluhan>() {
            @Override
            public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                Integer value=response.body().getValue();

                if(value==1) {
                    response.body();
                    Toast.makeText(HistoryDetail.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {

            }
        });
    }

    public void feedbackDislike(String feedback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        Call<ListKeluhan> call = api.updateUserFeedback(temp, feedback);

        call.enqueue(new Callback<ListKeluhan>() {
            @Override
            public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                Integer value=response.body().getValue();

                if(value==1) {
                    response.body();
                    Toast.makeText(HistoryDetail.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {

            }
        });
    }

    private void initKeluhan(){
        txtIsi = findViewById(R.id.frgdeskripsikeluhanact);
        txtTgl = findViewById(R.id.frgtglkeluhanact);
        txtStatus = findViewById(R.id.frgisistatusact);
        txtTujuan = findViewById(R.id.frgtujuankeluhanact);
        txtKomentar = findViewById(R.id.frgkomentarkeluhanact);
        txtFeedback = findViewById(R.id.frgfeedbackact);
        btnLike = findViewById(R.id.btnfeedbacklikeact);
        btnDislike = findViewById(R.id.btnfeedbackdislikeact);

        txtgivefeedback = findViewById(R.id.txtgivefeedback);
        cardFeedback = findViewById(R.id.cardViewFeedback);
    }

    void onClickLike(HistoryDetail v) {
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    feedbackLike("Good");
                } catch (Exception e) {
                }

            }
        });
    }

    void onClickDislike(HistoryDetail v) {
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    feedbackDislike("Bad");
                } catch (Exception e) {
                }

            }
        });
    }
}
