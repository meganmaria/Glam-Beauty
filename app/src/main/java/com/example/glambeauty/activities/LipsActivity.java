package com.example.glambeauty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;

import com.example.glambeauty.model.MakeUpList;
import com.example.glambeauty.R;
import com.example.glambeauty.api.ApiManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LipsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ConstraintLayout clLipstick, clLipliner;

    public MutableLiveData<ArrayList<MakeUpList>> makeupList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lips);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);

        clLipstick = findViewById(R.id.clLipstick);
        clLipliner = findViewById(R.id.clLipliner);

        clLipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLipstick = new Intent(LipsActivity.this, LipstickActivity.class);
                startActivity(intentLipstick);
            }
        });

        clLipliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLipliner = new Intent(LipsActivity.this, LiplinerActivity.class);
                startActivity(intentLipliner);
            }
        });

        ApiManager.getInstance().getMakeupService().getMakeUp().enqueue(new Callback<ArrayList<MakeUpList>>() {
            @Override
            public void onResponse(Call<ArrayList<MakeUpList>> call, Response<ArrayList<MakeUpList>> response) {
                if(response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    makeupList.setValue(response.body());
                    Log.d("MAKEUP", "onResponse: first->" + response.body().get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MakeUpList>> call, Throwable t) {
                Log.d("MAKEUP", "onFailure: " + t.getMessage()+" link"+call.request().url());
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(LipsActivity.this, PocetniActivity.class);
                startActivity(homeIntent);
                break;


            case R.id.favorite:
                Intent favoriteIntent = new Intent(LipsActivity.this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;

            case R.id.profil:
                Intent profileIntent = new Intent(LipsActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return false;
    }
}