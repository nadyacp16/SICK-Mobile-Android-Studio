package com.example.acer.askatma.models;

import com.google.gson.annotations.SerializedName;

public class KategoriLayanan {

    private String id_kategori;
    private String nama_kategori;


    public KategoriLayanan(String id, String nama){
        this.id_kategori = id;
        this.nama_kategori = nama;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }
}
