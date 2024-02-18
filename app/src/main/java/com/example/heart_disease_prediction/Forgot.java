package com.example.heart_disease_prediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forgot extends AppCompatActivity {

    EditText mobilenumber;
    Button otpbtn;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        mobilenumber=findViewById(R.id.number);
        progressBar=findViewById(R.id.progress);

        otpbtn=findViewById(R.id.otp);
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobilenumber.getText().toString().trim().isEmpty()){
                    Toast.makeText(Forgot.this,"Enter Mobile Number",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                otpbtn.setVisibility(View.INVISIBLE);
                // basically this method is used to initiate phone number verification process
                // Callbacks is used to handle different states of phone number verification process
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92"+mobilenumber.getText().toString().trim(),
                        60, TimeUnit.SECONDS,Forgot.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                otpbtn.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                otpbtn.setVisibility(View.VISIBLE);
                                Toast.makeText(Forgot.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                otpbtn.setVisibility(View.VISIBLE);
                                Intent intent=new Intent(getApplicationContext(),OTP.class);
                                intent.putExtra("number",mobilenumber.getText().toString());
                                intent.putExtra("verification",verification);
                                startActivity(intent);
                            }
                        }
                );
                Intent intent=new Intent(getApplicationContext(),OTP.class);
                intent.putExtra("number",mobilenumber.getText().toString());
                startActivity(intent);
            }
        });


    }
}