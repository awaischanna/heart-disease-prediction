package com.example.heart_disease_prediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class vitals extends AppCompatActivity {
    RadioButton male,female,alcoyes,alcono,smokeyes,smokeno;
    TextView header;

    TextInputLayout userage,usersystolicbp,userdiastolicbp,usercholesterol,userbloodsugar;

   DatabaseReference rootdatabase;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
        header=findViewById(R.id.head);

        register=findViewById(R.id.registration);
//
        userage=findViewById(R.id.age);
        usersystolicbp=findViewById(R.id.systolicbp);
        userdiastolicbp=findViewById(R.id.diastolicbp);
        usercholesterol=findViewById(R.id.cholesterol);
        userbloodsugar=findViewById(R.id.bloodsugar);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        alcoyes=findViewById(R.id.alchololyes);
        alcono=findViewById(R.id.alchololno);
        smokeyes=findViewById(R.id.smokingyes);
        smokeno=findViewById(R.id.smokingno);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Bundle bundle=getIntent().getExtras();
                String name=bundle.getString("namee");
                String phone_no=bundle.getString("phoneno");
                String password=bundle.getString("password");
                String email=bundle.getString("email");
                String age=userage.getEditText().getText().toString();
                String systolicbp=usersystolicbp.getEditText().getText().toString();
                String diastolicbp=userdiastolicbp.getEditText().getText().toString();
                String cholesterol=usercholesterol.getEditText().getText().toString();
                String bloodsugar=userbloodsugar.getEditText().getText().toString();

                String gender="";
                String alcohol="";
                String smoke="";
                if(male.isChecked()){
                    gender=male.getText().toString();
                }
                if(female.isChecked()){
                    gender=female.getText().toString();
                }
                if(smokeyes.isChecked()){
                    smoke=smokeyes.getText().toString();
                }
                if(smokeno.isChecked()){
                    smoke=smokeno.getText().toString();
                }
                if(alcoyes.isChecked()){
                    alcohol=alcoyes.getText().toString();
                }
                if(alcono.isChecked()){
                    alcohol=alcono.getText().toString();
                }
//
                HashMap hashMap=new HashMap();
                hashMap.put("Name",name);
                hashMap.put("Gender",gender);
                hashMap.put("Phone_no",phone_no);
                hashMap.put("Email",email);
                hashMap.put("Password",password);
                hashMap.put("Age",age);
                hashMap.put("Systolic_bp",systolicbp);
                hashMap.put("Diastolic_bp",diastolicbp);
                hashMap.put("Cholesterol",cholesterol);
                hashMap.put("Blood_sugar",bloodsugar);
                hashMap.put("Alcohol",alcohol);
                hashMap.put("Smoke",smoke);

                // getInstance method refers towards the root node of firabase database
                rootdatabase=FirebaseDatabase.getInstance().getReference("users");
                rootdatabase.child(phone_no).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(vitals.this,"your data store successfully",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(vitals.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(vitals.this,"your data not store successfully",Toast.LENGTH_SHORT).show();
                    }
                });
          }
        });

    }
}