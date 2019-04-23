package com.example.acer.askatma.models;

import java.util.ArrayList;
import java.util.List;

public class ListKeluhanAdmin {

    private List<Keluhan> keluhan = new ArrayList<>();
    private boolean error;

    public ListKeluhanAdmin() {
    }

    public List<Keluhan> getKeluhan() {
        return keluhan;
    }

    public boolean isError(){return error;}

    public void setKeluhan(List<Keluhan> keluhan) {
        this.keluhan = keluhan;
    }
}
