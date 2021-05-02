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

public class EyesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ConstraintLayout clEyebrow, clEyeliner, clEyeshadow, clMascara;

    public MutableLiveData<ArrayList<MakeUpList>> makeupList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);

        clEyebrow = findViewById(R.id.clEyebrow);
        clEyeliner = findViewById(R.id.clEyeliner);
        clEyeshadow = findViewById(R.id.clEyeshadow);
        clMascara = findViewById(R.id.clMaskara);


        clEyebrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEyebrow = new Intent(EyesActivity.this, EyebrowActivity.class);
                startActivity(intentEyebrow);
            }
        });

        clEyeliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEyeliner = new Intent(EyesActivity.this, EyelinerActivity.class);
                startActivity(intentEyeliner);
            }
        });

        clEyeshadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEyeshadow = new Intent(EyesActivity.this, EyeshadowActivity.class);
                startActivity(intentEyeshadow);
            }
        });

        clMascara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMascara = new Intent(EyesActivity.this, MascaraActivity.class);
                startActivity(intentMascara);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(EyesActivity.this, PocetniActivity.class);
                startActivity(homeIntent);
                break;


            case R.id.favorite:
                Intent favoriteIntent = new Intent(EyesActivity.this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;

            case R.id.profil:
                Intent profileIntent = new Intent(EyesActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return false;
    }
}

