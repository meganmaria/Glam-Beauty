package com.example.glambeauty.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.glambeauty.R;
import com.example.glambeauty.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsernameReg, etPasswordReg;
    Button btnReg;

    User user = new User();

    String userId;

    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceKorisnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        etUsernameReg = findViewById(R.id.etUsernameReg);
        etPasswordReg = findViewById(R.id.etPassReg);

        btnReg = findViewById(R.id.btnNewUser);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etUsernameReg.getText().toString().matches("")) {
                   user.setUsername(etUsernameReg.getText().toString());
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Unesite korisničko ime!", Toast.LENGTH_LONG).show();
                }

                if(!etPasswordReg.getText().toString().matches("")) {
                    user.setPassword(etPasswordReg.getText().toString());
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Unesite korisničko ime!", Toast.LENGTH_LONG).show();
                }

                String id;
                if(userId == null) {
                    id = databaseReference.push().getKey();
                }
                else {
                    id = userId;
                }
                user.setId(id);

                if(id != null) {
                    databaseReference.child(id).setValue(user);
                }

                finish();
            }
        });


    }
}
