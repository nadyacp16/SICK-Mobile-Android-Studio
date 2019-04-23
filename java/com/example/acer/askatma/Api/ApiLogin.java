package com.example.acer.askatma.Api;

import com.example.acer.askatma.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLogin {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userLogin(@Field("nomoridentitas") String nomoridentitas,
                                  @Field("password") String password);


    @FormUrlEncoded
    @POST("updateUserPassword.php")
    Call<LoginResponse> updateUserPassword(@Field("nomoridentitas")String nomoridentitas,
                                  @Field("password") String password,
                                  @Field("oldPassword") String oldPassword);
}
