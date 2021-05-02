package com.example.glambeauty.api;

import com.example.glambeauty.services.MakeupService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager instance;
    private MakeupService makeupService;

    private String BASE_URL = "https://makeup-api.herokuapp.com";

    private ApiManager() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        makeupService = retrofit.create(MakeupService.class);
    }

    public static  ApiManager getInstance() {
        if(instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public MakeupService getMakeupService() {
        return makeupService;
    }
}
