package com.example.acer.askatma.models;

import java.util.ArrayList;

public class ListKategoriLayanan {
    private ArrayList<KategoriLayanan> kategoriLayanan;
    private Integer value;

    public ListKategoriLayanan() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<KategoriLayanan> getCategories() {
        return kategoriLayanan;
    }

    public void setKategoriLayanan(ArrayList<KategoriLayanan> kategoriLayanan) {
        this.kategoriLayanan = kategoriLayanan;
    }
}
