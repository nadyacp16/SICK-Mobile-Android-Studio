package com.example.acer.askatma.Api;

public class ApiUrl {
    String URL="http://192.168.9.7/apimobilesick/";

    public void setURL(String URL) {
        this.URL = URL;
    }

    public ApiUrl(String URL) {

        this.URL = URL;
    }
    public static String GetMyURL()
    {
        return "http://192.168.9.7/apimobilesick/";
    }
}