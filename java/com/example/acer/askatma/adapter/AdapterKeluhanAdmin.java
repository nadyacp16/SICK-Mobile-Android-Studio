package com.example.acer.askatma.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.HistoryDetailAdmin;
import com.example.acer.askatma.models.Keluhan;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterKeluhanAdmin extends RecyclerView.Adapter<AdapterKeluhanAdmin.ViewHolder>  {
    private List<Keluhan> mData;
    private Context mCtx;

    public AdapterKeluhanAdmin(Context mCtx, List<Keluhan> mData) {
        this.mCtx = mCtx;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_keluhan_admin, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Keluhan keluhanView = mData.get(position);
        holder.txtTglKeluhan.setText(keluhanView.getTgl_pengaduan());
        holder.txtTujuanKeluhan.setText(keluhanView.getId_kategori());
        holder.txtid.setText(String.valueOf(keluhanView.getId_keluhan()));
        holder.txtStatusKeluhan.setText(keluhanView.getStatus());
        final String temp =  holder.txtid.getText().toString();
        holder.btnSeeDetailsHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(mCtx, HistoryDetailAdmin.class);
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
        public TextView txtTglKeluhan, txtid, txtStatusKeluhan;
        private Button btnSeeDetailsHistory;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTglKeluhan = (TextView) itemView.findViewById(R.id.txtTglKeluhanadmin);
            txtTujuanKeluhan = (TextView) itemView.findViewById(R.id.txtTujuanKeluhanadmin);
            txtStatusKeluhan = (TextView) itemView.findViewById(R.id.txtStatKeluhanadmin);
            btnSeeDetailsHistory = (Button) itemView.findViewById(R.id.btn_seeDetailsHistoryadmin);
            txtid = (TextView) itemView.findViewById(R.id.idkeladmin);
        }
    }
}
