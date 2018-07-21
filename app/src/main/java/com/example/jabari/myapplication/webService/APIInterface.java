package com.example.jabari.myapplication.webService;

import com.example.jabari.myapplication.model.Questionnaire;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("Home/Index")
    Call<List<Questionnaire>> getQuestionnaire();
}
