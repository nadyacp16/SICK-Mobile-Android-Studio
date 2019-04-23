package com.example.acer.askatma.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.HistoryDetail;
import com.example.acer.askatma.activities.MainActivity;
import com.example.acer.askatma.adapter.AdapterKeluhanPengguna;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class History extends Fragment {
    private RecyclerView recyclerViewHistory;
    private RecyclerView.Adapter adapter;

    private SharedPrefManager sharedPrefManager;
    private String temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_history,container,false);

        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(getActivity());
        super.onViewCreated(view, savedInstanceState);

        recyclerViewHistory = view.findViewById(R.id.recycleViewHistoryPengguna);
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        temp=sharedPrefManager.getSpIdPel();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeluhan api = retrofit.create(ApiKeluhan.class);

        Call<ListKeluhan> call = api.showComplaintById(temp);

        call.enqueue(new Callback<ListKeluhan>() {
            @Override
            public void onResponse(Call<ListKeluhan> call, final Response<ListKeluhan> response) {
                Integer value=response.body().getValue();

                if(value==1) {
                    response.body();
                    adapter = new AdapterKeluhanPengguna(getActivity(), response.body().getResult());

                    recyclerViewHistory.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ListKeluhan> call, Throwable t) {

            }
        });
    }
}
