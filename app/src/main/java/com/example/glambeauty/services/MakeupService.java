package com.example.glambeauty.services;

import com.example.glambeauty.model.MakeUpList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MakeupService {
    @GET("/api/v1/products.json")
    Call<ArrayList<MakeUpList>> getMakeUp();
}
