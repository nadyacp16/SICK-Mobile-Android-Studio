package com.example.acer.askatma.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.Fragment.History;
import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.HistoryDetail;
import com.example.acer.askatma.models.Keluhan;
import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterKeluhanPengguna extends RecyclerView.Adapter<AdapterKeluhanPengguna.ViewHolder>{
    private List<Keluhan> mData;
    private Context mCtx;

    public AdapterKeluhanPengguna(Context mCtx, List<Keluhan> mData){
        this.mCtx = mCtx;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mCtx).inflate(R.layout.recycle_history,parent,false);
        final ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Keluhan keluhanView = mData.get(position);
        holder.txtTglKeluhan.setText(keluhanView.getTgl_pengaduan());
        holder.txtTujuanKeluhan.setText(keluhanView.getId_kategori());
        holder.txtid.setText(String.valueOf(keluhanView.getId_keluhan()));
        final String temp =  holder.txtid.getText().toString();
        holder.btnSeeDetailsHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(mCtx, HistoryDetail.class);
                i.putExtra("kode_keluhan", temp);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTujuanKeluhan;
        public TextView txtTglKeluhan, txtid;
        private Button btnSeeDetailsHistory;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTglKeluhan = (TextView) itemView.findViewById(R.id.txtTglKeluhan);
            txtTujuanKeluhan = (TextView) itemView.findViewById(R.id.txtTujuanKeluhan);
            btnSeeDetailsHistory = (Button) itemView.findViewById(R.id.btn_seeDetailsHistory);
            txtid = (TextView) itemView.findViewById(R.id.idkel);
        }
    }
}
