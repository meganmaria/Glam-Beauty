package com.example.glambeauty.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.glambeauty.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private TextView nameTextView;
    private ImageView userImageView;
    private String username, password;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static final String USERS = "users";


    String userId;

    BottomNavigationView bottomNavigationView;

    SharedPreferences sharedPreferences;
    final String MY_PREFERENCES = "USER_DATA";

    ConstraintLayout textView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", "emptyString");

        nameTextView = findViewById(R.id.textView);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.profil).setChecked(true);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(USERS);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(!dataSnapshot1.child("id").getValue().equals("") && dataSnapshot1.child("id").getValue().equals(userId)){
                        Log.d("username", "onDataChange: " + dataSnapshot1.child("username").toString());
                        nameTextView.setText(dataSnapshot1.child("username").getValue(String.class));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent homeIntent = new Intent(ProfileActivity.this, PocetniActivity.class);
                    startActivity(homeIntent);
                    break;

                case R.id.favorite:
                    Intent favoriteIntent = new Intent(ProfileActivity.this, FavoriteActivity.class);
                    startActivity(favoriteIntent);
                    break;

                case R.id.profil:
                    Toast.makeText(ProfileActivity.this, "Profile activity", Toast.LENGTH_LONG).show();
                    break;
            }
        return false;
    }
}
