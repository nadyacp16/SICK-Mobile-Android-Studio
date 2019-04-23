package com.example.acer.askatma.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "SICK_APP";

    public static final String KEY_USER_ID = "nomoridentitas";
    public static final String KEY_USER_NAME = "nama";
    public static final String KEY_ID_KELUHAN = "kode_keluhan";
    public static final String KEY_ID_PERAN = "id_peran";


    public static final String SP_ALREADY_LOGIN ="spAlreadyLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        this.mCtx = context;
        sp=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        spEditor=sp.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveSPString(String keySP,String value){
        spEditor.putString(keySP,value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_ID, null) != null)
            return true;
        return false;
    }

//    public User getUser() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new User(
//                sharedPreferences.getString(KEY_USER_ID, null),
//                sharedPreferences.getString(KEY_USER_NAME, null),
//                sharedPreferences.getInt(KEY_USER_ROLE, -1)
//        );
//    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public  String getSpIdPel() {
        return sp.getString(KEY_USER_ID,"");
    }

    public  String getSpIdKel() {
        return sp.getString(KEY_ID_KELUHAN,"");
    }

    public  String getSpNama() {
        return sp.getString(KEY_USER_NAME,"");
    }

    public  String getSpidPeran () {
        return sp.getString(KEY_ID_PERAN,"");
    }

    public  Boolean getSpAlreadyLogin() {
        return sp.getBoolean(SP_ALREADY_LOGIN,false);
    }
}
