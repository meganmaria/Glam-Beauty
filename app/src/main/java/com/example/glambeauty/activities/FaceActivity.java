package com.example.glambeauty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;

import com.example.glambeauty.R;
import com.example.glambeauty.model.MakeUpList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FaceActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ConstraintLayout clFoundation, clBronzer, clBlush;

    public MutableLiveData<ArrayList<MakeUpList>> makeupList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);

        clFoundation = findViewById(R.id.clFoundation);
        clBronzer = findViewById(R.id.clBronzer);
        clBlush = findViewById(R.id.clBlush);

        clFoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFoundation = new Intent(FaceActivity.this, FoundationActivity.class);
                startActivity(intentFoundation);
            }
        });

        clBronzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBronzer = new Intent(FaceActivity.this, BronzerActivity.class);
                startActivity(intentBronzer);
            }
        });

        clBlush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBlush = new Intent(FaceActivity.this, BlushActivity.class);
                startActivity(intentBlush);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(FaceActivity.this, PocetniActivity.class);
                startActivity(homeIntent);
                break;


            case R.id.favorite:
                Intent favoriteIntent = new Intent(FaceActivity.this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;

            case R.id.profil:
                Intent profileIntent = new Intent(FaceActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return false;
    }
}