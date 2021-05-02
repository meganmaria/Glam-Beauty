package com.example.glambeauty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.R;
import com.example.glambeauty.adapter.RecyclerViewAdapterFav;
import com.example.glambeauty.model.ProductList;
import com.example.glambeauty.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoviProizvodActivity extends AppCompatActivity {

    RecyclerViewAdapterFav adapterSastojci;
    RecyclerViewAdapterFav adapterKoraci;
    ArrayList<String> lSastojci;
    ArrayList<String> lKoraci;
    ImageButton btnDodajSastojak;
    ImageButton btnDodajKorak;
    EditText etSastojak;
    EditText etKorak;
    EditText etNazivProizvoda;
    Button btnSpremiProizvod;
    DatabaseReference reff;
    DatabaseReference proizvodReference;
    ProductList productList = new ProductList();
    String userId;
    String proizvodId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_proizvod);

        reff = FirebaseDatabase.getInstance().getReference().child("ProductList");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        etNazivProizvoda = findViewById(R.id.etNazivProizvoda);

        btnDodajSastojak = findViewById(R.id.btnDodajSastojak);
        etSastojak = findViewById(R.id.etSastojak);

        btnDodajKorak = findViewById(R.id.btnDodajKorak);
        etKorak = findViewById(R.id.etKorak);

        lSastojci = new ArrayList<>();
        lKoraci = new ArrayList<>();

        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getExtras().get("idProizvoda").toString().matches("")){
            Toast.makeText(this, intent.getExtras().get("idProizvoda").toString(), Toast.LENGTH_SHORT).show();
            proizvodId = intent.getExtras().get("idProizvoda").toString();
            proizvodReference = reff.child(proizvodId);
            proizvodReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    productList = dataSnapshot.getValue(ProductList.class);

                    etNazivProizvoda.setText(productList.getNazivProizvoda());

                    lSastojci.addAll(productList.getListaSastojci());
                    lKoraci.addAll(productList.getListaKoraci());

                    adapterSastojci.notifyDataSetChanged();
                    adapterKoraci.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        SetupRecyclerView(R.id.recyclerViewSastojci, lSastojci);
        SetupRecyclerView(R.id.recyclerViewKoraci, lKoraci);

        btnDodajSastojak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sastojak = etSastojak.getText().toString();

                if(!sastojak.matches("")){
                    lSastojci.add(sastojak);
                    adapterSastojci.notifyDataSetChanged();

                    etSastojak.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Upisi od cega zelis da se sastoji proizvod(brand, boja)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDodajKorak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String korak = etKorak.getText().toString();

                if(!korak.matches("")){
                    lKoraci.add(korak);
                    adapterKoraci.notifyDataSetChanged();

                    etKorak.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Upisi korake koristenja proizvoda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //productList = new ProductList();

        btnSpremiProizvod = (Button) findViewById(R.id.btnSpremiProizvod);

        btnSpremiProizvod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etNazivProizvoda.getText().toString().matches("")){
                    productList.setNazivProizvoda(etNazivProizvoda.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unesi naziv proizvoda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!lSastojci.isEmpty() && !lKoraci.isEmpty()){
                    productList.setListaSastojci(lSastojci);
                    productList.setListaKoraci(lKoraci);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Niste unijeli sastojke ili korake", Toast.LENGTH_SHORT).show();
                    return;
                }

                userId = User.GetUserId(getApplicationContext());

                if(!userId.matches("")){
                    productList.setId(userId);
                }

                String id;
                if(proizvodId == null){
                    id = reff.push().getKey();
                }
                else {
                    id = proizvodId;
                }
                productList.setnId(id);

                if(id != null){
                    reff.child(id).setValue(productList);
                }

                finish();
            }
        });

    }

    public void SetupRecyclerView(int resource, ArrayList<String>lista){
        RecyclerView rv = findViewById(resource);
        RecyclerViewAdapterFav adapter = new RecyclerViewAdapterFav(lista, R.layout.list_fav_item);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if(resource == R.id.recyclerViewSastojci){
            adapterSastojci = adapter;
        }
        else {
            adapterKoraci = adapter;
        }
    }

}




