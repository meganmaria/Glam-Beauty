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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.R;
import com.example.glambeauty.adapter.RecyclerViewAdapterListFav;
import com.example.glambeauty.model.ProductList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class FavoriteActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    DatabaseReference databaseReference;
    ProductList productList = new ProductList();
    ArrayList<ProductList> productLists = new ArrayList<>();

    ArrayList<String> listaId = new ArrayList<>();


    FloatingActionButton btnDodaj;

    SharedPreferences sharedPreferences;
    final String MY_PREFERENCES = "USER";
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        String MY_PREFERENCES = "USER_DATA";
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        if(sharedPreferences.getString("user_id", "emptyString").matches("emptyString"))
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE).edit();
            String uniqueID = UUID.randomUUID().toString();
            editor.putString("user_id", uniqueID);
            editor.apply();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productLists.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    productList = snapshot.getValue(ProductList.class);
                    productLists.add(productList);
                }
                setRecyclerViewProductList(productLists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Error", databaseError.toString());

            }
        });

        btnDodaj = findViewById(R.id.dodaj);
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoriteActivity.this, NoviProizvodActivity.class);
                startActivity(intent);
            }
        });


        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.favorite).setChecked(true);
    }

    public void setRecyclerViewProductList(ArrayList<ProductList> productList){
        RecyclerView recyclerView = findViewById(R.id.mojRecycler);
        RecyclerViewAdapterListFav recyclerViewAdapterListFav = new RecyclerViewAdapterListFav(productList, getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapterListFav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(FavoriteActivity.this, PocetniActivity.class);
                startActivity(homeIntent);
                break;

            case R.id.favorite:
                Toast.makeText(FavoriteActivity.this, "Favorite activity", Toast.LENGTH_LONG).show();
                break;

            case R.id.profil:
                Intent profileIntent = new Intent(FavoriteActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return false;
    }

}

