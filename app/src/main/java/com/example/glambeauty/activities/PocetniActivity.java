package com.example.glambeauty.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.glambeauty.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PocetniActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ConstraintLayout clEyes, clLips, clFace;
    String userId;

    SharedPreferences sharedPreferences;
    final String MY_PREFERENCES = "USER_DATA";


      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", "emptyString");

        Log.d("userID", "onCreate: " + userId.toString());


        clEyes = findViewById(R.id.clEyes);
        clLips = findViewById(R.id.clLips);
        clFace = findViewById(R.id.clFace);

        clEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEyes = new Intent(PocetniActivity.this, EyesActivity.class);
                startActivity(intentEyes);
            }
        });

        clLips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLips = new Intent(PocetniActivity.this, LipsActivity.class);
                startActivity(intentLips);
            }
        });

        clFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFace = new Intent(PocetniActivity.this, FaceActivity.class);
                startActivity(intentFace);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(PocetniActivity.this, "Home activity", Toast.LENGTH_LONG).show();
                break;

            case R.id.favorite:
                Intent favoriteIntent = new Intent(PocetniActivity.this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;

            case R.id.profil:
                Intent profileIntent = new Intent(PocetniActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return false;
    }
}