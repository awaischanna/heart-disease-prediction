package com.example.heart_disease_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity {

    TextView username,age,gender,systolic,diastolic,cholesterol,bloodsugar,alcohol,smoke;
    Button predict,logout;

    DatabaseReference rootnode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//
//        username=findViewById(R.id.name);
//        age=findViewById(R.id.age);
//        gender=findViewById(R.id.gender);
//        systolic=findViewById(R.id.systolic_bp);
//        diastolic=findViewById(R.id.diastolic_bp);
//        cholesterol=findViewById(R.id.cholesterol);
//        bloodsugar=findViewById(R.id.blood_sugar);
//        smoke=findViewById(R.id.smoking);
//        alcohol=findViewById(R.id.alcohol);
//
//        Bundle bundle=getIntent().getExtras();
//        String name_=bundle.getString("name");
//        String age_=bundle.getString("age");
//        String gender_=bundle.getString("gender");
//        String systolicbp_=bundle.getString("systolicbp");
//        String diastolicbp_=bundle.getString("diastolicbp");
//        String cholesterol_=bundle.getString("cholesterol");
//        String bloodsugar_=bundle.getString("bloodsugar");
//        String alcohol_=bundle.getString("alcohol");
//        String smoke_=bundle.getString("smoke");
//
//        username.setText(name_);
//        age.setText(age_);
//        gender.setText(gender_);
//        systolic.setText(systolicbp_);
//        diastolic.setText(diastolicbp_);
//        cholesterol.setText(cholesterol_);
//        bloodsugar.setText(bloodsugar_);
//        smoke.setText(smoke_);
//        alcohol.setText(alcohol_);
//
//        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Dashboard.this, LoginActivity.class);
//                startActivity(intent);
//                finish();

            }
        });

        //predict=findViewById(R.id.predict);

    }
}