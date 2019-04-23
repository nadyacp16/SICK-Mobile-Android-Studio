package com.example.acer.askatma.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.MainActivity;
import com.example.acer.askatma.adapter.AdapterKeluhanAdmin;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeAdmin extends Fragment {
    private SharedPrefManager sharedPrefManager;
    private TextView txtcountnotproc, txtcountforw, txtcountproc, txtcountfin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_home_admin,container,false);

        txtcountnotproc = view.findViewById(R.id.countnotprocessedcomplaints);
        txtcountforw = view.findViewById(R.id.countforwardedcomplaints);
        txtcountproc = view.findViewById(R.id.countprocessedcomplaints);
        txtcountfin = view.findViewById(R.id.countfinishedcomplaints);

        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        Call<ListKeluhan> call = api.showCount();

        call.enqueue(new Callback<ListKeluhan>() {
            @Override
            public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                Integer value = response.body().getValue();

                if (value == 1) {
                    response.body();
                        String countnotproc, countforw, countproc, countfin;
                        countnotproc = String.valueOf(response.body().getResultcountnotproc().get(0).getCountnotproc());
                        countforw = String.valueOf(response.body().getResultcountforwarded().get(0).getCountforw());
                        countproc = String.valueOf(response.body().getResultcountprocessed().get(0).getCountproc());
                        countfin = String.valueOf(response.body().getResultcountfinished().get(0).getCountfin());

                        txtcountnotproc.setText(countnotproc);
                        txtcountforw.setText(countforw);
                        txtcountproc.setText(countproc);
                        txtcountfin.setText(countfin);
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {
            }
        });
    }
}
