package com.example.acer.askatma.models;

import android.support.annotation.StringRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keluhan {
    @SerializedName("kode_keluhan")
    @Expose
    private int id_keluhan;

    @SerializedName("nama_kategori")
    @Expose
    private String id_kategori;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("userfk")
    @Expose
    private String user;

    @SerializedName("tanggal_pengaduan")
    @Expose
    private String tgl_pengaduan;

    @SerializedName("isi_keluhan")
    @Expose
    private String isi;

    @SerializedName("namatpkp")
    private String namatpkp;

    @SerializedName("feedback")
    @Expose
    private String feedback;

    @SerializedName("komentar_keluhan")
    private String komentar;

    @SerializedName("countnotproc")
    private int countnotproc;

    @SerializedName("countforw")
    private int countforw;

    @SerializedName("countproc")
    private int countproc;

    @SerializedName("countfin")
    private int countfin;

    public int getCountnotproc() {
        return countnotproc;
    }

    public int getCountforw() {
        return countforw;
    }

    public int getCountproc() {
        return countproc;
    }

    public int getCountfin() {
        return countfin;
    }


    public String getKomentar() {
        return komentar;
    }

    public int getId_keluhan() {
        return id_keluhan;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public String getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }

    public String getTgl_pengaduan() {
        return tgl_pengaduan;
    }

    public String getIsi() {
        return isi;
    }

    public String getNamatpkp() {
        return namatpkp;
    }

    public String getFeedback() {
        return feedback;
    }

}
