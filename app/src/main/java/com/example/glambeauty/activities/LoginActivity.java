package com.example.glambeauty.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.glambeauty.R;
import com.example.glambeauty.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText etUsername, etPassword;

    User user = new User();

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(!etUsername.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                            Integer login = 0;
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                user = snapshot.getValue(User.class);
                                Log.d("nesto", "onDataChange: " + user.getId());



                                String username = dataSnapshot.child(user.getId()).child("username").getValue().toString();
                                String password = dataSnapshot.child(user.getId()).child("password").getValue().toString();
                                if(etUsername.getText().toString().equals(username))
                                {
                                    if(etPassword.getText().toString().equals(password)) {

                                        String MY_PREFERENCES = "USER_DATA";
                                        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
                                        if(!sharedPreferences.getString("user_id", "emptyString").matches("emptyString"))
                                        {
                                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE).edit();
                                            editor.putString("user_id", user.getId());
                                            editor.apply();
                                        }

                                        login++;
                                        Intent intent = new Intent(LoginActivity.this, PocetniActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                            if(login == 0) {
                                Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentReg);
            }
        });
    }
}
