package com.example.acer.askatma.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.askatma.R;
import com.example.acer.askatma.models.Keluhan;

import java.util.List;

public class AdapterDetailKeluhanPengguna extends RecyclerView.Adapter<AdapterDetailKeluhanPengguna.ViewHolder> {
    private List<Keluhan> mData;
    private Context mCtx;
    private String temp;

    public AdapterDetailKeluhanPengguna(Context mCtx, List<Keluhan> mData){
        this.mCtx = mCtx;
        this.mData = mData;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mCtx).inflate(R.layout.recycle_detailhistory, parent, false);
        final ViewHolder v = new ViewHolder(view);

        return v;
    }

    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Keluhan keluhanView = mData.get(position);
        holder.txtTglKeluhan.setText(keluhanView.getTgl_pengaduan());
        holder.txtTujuanKeluhan.setText(keluhanView.getId_kategori());
        holder.txtDesc.setText(keluhanView.getIsi());
        holder.txtStatus.setText(keluhanView.getStatus());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTujuanKeluhan;
        public TextView txtTglKeluhan, txtDesc, txtStatus;
        private Button btnFeedbackLike, btnFeedbackDislike;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTglKeluhan = (TextView) itemView.findViewById(R.id.frgtglkeluhan);
            txtTujuanKeluhan = (TextView) itemView.findViewById(R.id.frgtujuankeluhan);
            txtDesc = (TextView) itemView.findViewById(R.id.frgdeskripsikeluhan);
            txtStatus = (TextView) itemView.findViewById(R.id.frgisistatus);
            btnFeedbackLike = (Button) itemView.findViewById(R.id.btnfeedbacklike);
            btnFeedbackDislike = (Button) itemView.findViewById(R.id.btnfeedbackdislike);
        }
    }
}
