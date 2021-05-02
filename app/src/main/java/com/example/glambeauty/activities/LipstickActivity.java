package com.example.glambeauty.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.adapter.MakeUpRecyclerViewAdapter;
import com.example.glambeauty.model.MakeUpList;
import com.example.glambeauty.R;
import com.example.glambeauty.api.ApiManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LipstickActivity extends AppCompatActivity {

    public MutableLiveData<ArrayList<MakeUpList>> makeupList = new androidx.lifecycle.MutableLiveData<>();public ArrayList<MakeUpList> list_makeup = new ArrayList<>();
    MakeUpList makeUpList = new MakeUpList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipstick);

        ApiManager.getInstance().getMakeupService().getMakeUp().enqueue(new Callback<ArrayList<MakeUpList>>() {
            @Override
            public void onResponse(Call<ArrayList<MakeUpList>> call, Response<ArrayList<MakeUpList>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    Log.d("itemFromResponse", "onResponse lipstick : " + response.body().get(8).getProduct_image_link());
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).getProduct_type().equals("lipstick") && !response.body().get(i).getDescription().equals("") && !response.body().get(i).getBrand().equals("nyx")) {
                            //  Log.d("itemFromResponse", "onResponse: " + response.body().get(5).getProduct_type());
                            makeUpList = response.body().get(i);
                            list_makeup.add(makeUpList);
                        }
                    }
                    Log.d("itemFromResponse", "onResponse: lipstick first->" + response.body().get(5).getName());
                }
                setRecyclerViewMakeUp(list_makeup);
            }

            @Override
            public void onFailure(Call<ArrayList<MakeUpList>> call, Throwable t) {
                Log.d("itemFromResponse", "onFailure: " + t.getMessage() + " link" + call.request().url());
                t.printStackTrace();
            }
        });
    }

    public void setRecyclerViewMakeUp(ArrayList<MakeUpList> makeUps) {
        RecyclerView recyclerView = findViewById(R.id.lipstick_recycler_view);
        MakeUpRecyclerViewAdapter makeUpRecyclerViewAdapter = new MakeUpRecyclerViewAdapter(makeUps, getApplicationContext());
        recyclerView.setAdapter(makeUpRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}
