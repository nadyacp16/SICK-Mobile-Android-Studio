package com.example.acer.askatma.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<User> result = null;

    @SerializedName("user")
    private User user;

    public LoginResponse(Integer value, User user) {
        this.value = value;
        this.user = user;
    }


    @SerializedName("resultcountnotprocessed")
    private int countnotproc;

    @SerializedName("resultcountforwarded")
    private int countforw;

    @SerializedName("resultcountprocessed")
    private int countproc;

    @SerializedName("resultcountfinished")
    private int countfin;

    public int getCountnotproc() {
        return countnotproc;
    }

    public void setCountnotproc(int countnotproc) {
        this.countnotproc = countnotproc;
    }

    public int getCountforw() {
        return countforw;
    }

    public void setCountforw(int countforw) {
        this.countforw = countforw;
    }

    public int getCountproc() {
        return countproc;
    }

    public void setCountproc(int countproc) {
        this.countproc = countproc;
    }

    public int getCountfin() {
        return countfin;
    }

    public void setCountfin(int countfin) {
        this.countfin = countfin;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<User> getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
