package com.example.heart_disease_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
    Button login_btn, registration;
    //    FirebaseDatabase rootnode;
//    DatabaseReference reference;
    TextInputLayout fullname, phone, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration = findViewById(R.id.registration);
        login_btn = findViewById(R.id.login);
        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phoneno);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!validate_name() || !validate_phoneno() || !validate_password()){
                //  return;
                //}
                //else {
                Intent intent = new Intent(registration.this, LoginActivity.class);
                startActivity(intent);

                //}
            }
        });

//        registration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
//                Intent intent = new Intent(registration.this, vitals.class);
//                intent.putExtra("namee", fullname.getEditText().getText().toString());
//                intent.putExtra("phoneno", phone.getEditText().getText().toString());
//                intent.putExtra("password", password.getEditText().getText().toString());
//                intent.putExtra("email", email.getEditText().getText().toString());
//                startActivity(intent);
//            }
//        });

    }

    private Boolean validate_name() {
        String value = fullname.getEditText().getText().toString();
        if (value.isEmpty()) {
            fullname.setError("Field cannot be empty");
            return false;
        } else {
            fullname.setError(null);
            return true;
        }
    }

    private Boolean validate_email() {
        String value = email.getEditText().getText().toString();
        if (value.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private Boolean validate_phoneno() {
        String value = phone.getEditText().getText().toString();
        if (value.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        }
        else if(value.length()<11){
            phone.setError("Invalid number");
            return false;
        }else {
            phone.setError(null);
            return true;
        }
    }

    private Boolean validate_password() {
        String value = password.getEditText().getText().toString();
        if (value.isEmpty()) {
            password.setError("Field cannot be empty");
            //password.setEnabled(false);
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void registeruser(View view) {
        if (!validate_name() | !validate_phoneno() | !validate_password() | !validate_email()) {
            return;
        }

        Intent intent = new Intent(registration.this, vitals.class);
        intent.putExtra("namee", fullname.getEditText().getText().toString());
        intent.putExtra("phoneno", phone.getEditText().getText().toString());
        intent.putExtra("password", password.getEditText().getText().toString());
        intent.putExtra("email", email.getEditText().getText().toString());
        startActivity(intent);

    }
}
