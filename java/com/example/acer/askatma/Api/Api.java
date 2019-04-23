package com.example.acer.askatma.Api;

import com.example.acer.askatma.models.ListKategoriLayanan;
import com.example.acer.askatma.models.ListKeluhanAdmin;
import com.example.acer.askatma.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userlogin(@Field("nomoridentitas") String nomoridentitas,
                                 @Field("password") String password);

    @GET("allkategori")
    Call<ListKategoriLayanan> getCategories();


    @GET("allkeluhanAdmin")
    Call<ListKeluhanAdmin> getComplaints();
}
