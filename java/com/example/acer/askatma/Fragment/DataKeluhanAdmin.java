package com.example.acer.askatma.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.askatma.Api.Api;
import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.adapter.AdapterKeluhanAdmin;
import com.example.acer.askatma.adapter.AdapterKeluhanPengguna;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.models.ListKeluhanAdmin;
import com.example.acer.askatma.models.Value;
import com.example.acer.askatma.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataKeluhanAdmin extends Fragment {
    private RecyclerView recyclerViewAdmin;
    private RecyclerView.Adapter adapter;
    private String tempidperan, tempidtpkplogin;
    private SharedPrefManager sharedPrefManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getContext());
        tempidperan = sharedPrefManager.getSpidPeran();
        tempidtpkplogin = sharedPrefManager.getSpIdPel();
        View view = inflater.inflate(R.layout.fragment_data_keluhan_admin,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAdmin = view.findViewById(R.id.recycleViewKeluhanAdmin);
        recyclerViewAdmin.setHasFixedSize(true);
        recyclerViewAdmin.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        if(tempidperan.equals("1")) {

            Call<ListKeluhan> call = api.showComplaint();

            call.enqueue(new Callback<ListKeluhan>() {
                @Override
                public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                    Integer value = response.body().getValue();

                    if (value == 1) {
                        response.body();
                        adapter = new AdapterKeluhanAdmin(getActivity(), response.body().getResult());
                        recyclerViewAdmin.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ListKeluhan> call, Throwable t) {

                }
            });
        }
        else{
            Call<ListKeluhan> call = api.showComplaintTpkp(tempidtpkplogin);

            call.enqueue(new Callback<ListKeluhan>() {
                @Override
                public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                    Integer value = response.body().getValue();

                    if (value == 1) {
                        response.body();
                        adapter = new AdapterKeluhanAdmin(getActivity(), response.body().getResult());
                        recyclerViewAdmin.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ListKeluhan> call, Throwable t) {

                }
            });
        }
    }
}
