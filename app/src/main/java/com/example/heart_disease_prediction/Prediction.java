package com.example.heart_disease_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Prediction extends AppCompatActivity {
    Button back;
    TextView resulttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        resulttxt=findViewById(R.id.result);
        Bundle bundle=getIntent().getExtras();
        String result=bundle.getString("result");
        resulttxt.setText(result);
//        back=findViewById(R.id.backbtn);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Prediction.this, Profile.class);
//                startActivity(intent);
//            }
//        });
    }
}