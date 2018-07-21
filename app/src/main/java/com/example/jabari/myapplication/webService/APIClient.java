package com.example.jabari.myapplication.webService;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String Base_Url = "http://jabari.domain.com/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(Context context){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
