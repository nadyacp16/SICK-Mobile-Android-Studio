package com.example.acer.askatma.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    @SerializedName("nomoridentitas")
    private String nomoridentitas;

    @Expose
    @SerializedName("nama")
    private String nama;


    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("id_peran")
    private int id_peran;

    private String message;
    private Integer value;

    public User(String nomorid, String name, String pass, int idperan) {
        this.nomoridentitas = nomorid;
        this.nama = name;
        this.password = pass;
        this.id_peran = idperan;
    }

    public String getId() {
        return nomoridentitas;
    }

    public String getName() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public int getIdperan() {
        return id_peran;
    }

    public String getMessage() {
        return message;
    }

    public Integer getValue() {
        return value;
    }
}
