package com.example.acer.askatma.models;

import java.util.List;

public class ListKeluhan {
    private Integer value;
    private String message;
    private List<Keluhan> result;

    private List<Keluhan> resultcountnotproc;
    private List<Keluhan> resultcountforwarded;
    private List<Keluhan> resultcountprocessed;
    private List<Keluhan> resultcountfinished;

    public List<Keluhan> getResultcountnotproc() {
        return resultcountnotproc;
    }

    public List<Keluhan> getResultcountforwarded() {
        return resultcountforwarded;
    }

    public List<Keluhan> getResultcountprocessed() {
        return resultcountprocessed;
    }

    public List<Keluhan> getResultcountfinished() {
        return resultcountfinished;
    }

    public List<Keluhan> getResult() {
        return result;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
