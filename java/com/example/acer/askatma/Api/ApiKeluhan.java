package com.example.acer.askatma.Api;

import com.example.acer.askatma.models.ListKeluhan;
import com.example.acer.askatma.models.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiKeluhan {

    @GET("showComplaint.php")
    Call<ListKeluhan> showComplaint();

    @GET("showCount.php")
    Call<ListKeluhan> showCount();

    @FormUrlEncoded
    @POST("showComplaintTpkp.php")
    Call<ListKeluhan> showComplaintTpkp(@Field("id_tpkpfk") String id_tpkpfk);

    @FormUrlEncoded
    @POST("createComplaintBy.php")
    Call<Value> createComplaint(@Field("id_kategori") String id_kategori,
                                @Field("isi_keluhan") String isi_keluhan,
                                @Field("userfk") String userfk);

    @FormUrlEncoded
    @POST("showComplaintById.php")
    Call<ListKeluhan> showComplaintById(@Field("userfk") String userfk);

    @FormUrlEncoded
    @POST("showComplaintByIdKeluhan.php")
    Call<ListKeluhan> showComplaintByIdKeluhan(@Field("kode_keluhan") String kode_keluhan);

    @FormUrlEncoded
    @POST("updateUserFeedback.php")
    Call<ListKeluhan> updateUserFeedback(@Field("kode_keluhan") String kode_keluhan,
                                         @Field("feedback") String feedback);
}
