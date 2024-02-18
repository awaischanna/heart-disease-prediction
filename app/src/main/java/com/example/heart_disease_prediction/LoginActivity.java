package com.example.heart_disease_prediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button signup_btn,login,forgot;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
//        login=findViewById(R.id.login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginUser();
//            }
//        });


        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, Forgot.class);
                startActivity(intent);
                finish();
            }
        });
        signup_btn=findViewById(R.id.signup);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,registration.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }else if(val.length()<11){
            username.setError("Invalid number");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {

        String entered_username=username.getEditText().getText().toString();
        String entered_password=password.getEditText().getText().toString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUser=reference.orderByChild("Phone_no").equalTo(entered_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordfromdb=snapshot.child(entered_username).child("Password").getValue(String.class);
                    if(passwordfromdb.equals(entered_password)){

                        password.setError(null);
                        password.setErrorEnabled(false);

                        String db_name=snapshot.child(entered_username).child("Name").getValue(String.class);
                        String db_age=snapshot.child(entered_username).child("Age").getValue(String.class);
                        String db_gender=snapshot.child(entered_username).child("Gender").getValue(String.class);
                        String db_phone=snapshot.child(entered_username).child("Phone_no").getValue(String.class);
                        String db_systolicbp=snapshot.child(entered_username).child("Systolic_bp").getValue(String.class);
                        String db_diastolicbp=snapshot.child(entered_username).child("Diastolic_bp").getValue(String.class);
                        String db_cholesterol=snapshot.child(entered_username).child("Cholesterol").getValue(String.class);
                        String db_bloodsugar=snapshot.child(entered_username).child("Blood_sugar").getValue(String.class);
                        String db_alcohol=snapshot.child(entered_username).child("Alcohol").getValue(String.class);
                        String db_smoke=snapshot.child(entered_username).child("Smoke").getValue(String.class);

                        Intent intent=new Intent(LoginActivity.this,Profile.class);
                        intent.putExtra("name",db_name);
                        intent.putExtra("age",db_age);
                        intent.putExtra("phoneno",db_phone);
                        intent.putExtra("gender",db_gender);
                        intent.putExtra("systolicbp",db_systolicbp);
                        intent.putExtra("diastolicbp",db_diastolicbp);
                        intent.putExtra("cholesterol",db_cholesterol);
                        intent.putExtra("bloodsugar",db_bloodsugar);
                        intent.putExtra("alcohol",db_alcohol);
                        intent.putExtra("smoke",db_smoke);

                        startActivity(intent);
                        finish();



                    }
                    else{
                        password.setError("Invalid Password");
                        // it will start point towards the specific fields with help of request focus
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such user exist with that number");
                    username.requestFocus();
                }
            }
            // onCancelled method is called if the query is cancelled or if an error occured
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();
            }
        });

    }
}