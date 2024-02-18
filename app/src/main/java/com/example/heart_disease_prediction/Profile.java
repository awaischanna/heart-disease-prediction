package com.example.heart_disease_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextInputLayout systolicbp,diastolicbp,cholesterol,bloodsugar;
    TextView name,phoneno,age,gender,alco,smoke;

    String gen,choles,gluc,smok,alcoo;
    Button logout,predict;

    String url="https://cardio-ml-app-05f004bd803b.herokuapp.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout=findViewById(R.id.logout);
        predict=findViewById(R.id.predict);

        name=findViewById(R.id.username);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        phoneno=findViewById(R.id.phone);
        systolicbp=findViewById(R.id.systolicbp);
        diastolicbp=findViewById(R.id.diastolicbp);
        cholesterol=findViewById(R.id.cholesterol);
        bloodsugar=findViewById(R.id.bloodsugar);

        // here we are getting from login activity
        Bundle bundle=getIntent().getExtras();
        String name_=bundle.getString("name");
        String age_=bundle.getString("age");
        String phone_=bundle.getString("phoneno");
        String gender_=bundle.getString("gender");
        String systolicbp_=bundle.getString("systolicbp");
        String diastolicbp_=bundle.getString("diastolicbp");
        String cholesterol_=bundle.getString("cholesterol");
        String bloodsugar_=bundle.getString("bloodsugar");
        String alcohol_=bundle.getString("alcohol");
        String smoke_=bundle.getString("smoke");

        name.setText(name_);
        age.setText(age_);
        gender.setText(gender_);
        phoneno.setText(phone_);
        systolicbp.getEditText().setText(systolicbp_);
        diastolicbp.getEditText().setText(diastolicbp_);
        cholesterol.getEditText().setText(cholesterol_);
        bloodsugar.getEditText().setText(bloodsugar_);

        //gender conversion
        if(gender_.equals("Male")){
            gen= "2";
        }else{
            gen="1";
        }

        //cholesterol conversion
        int chol=Integer.parseInt(cholesterol_);
        if(chol<=200){
            choles="1";
        }else if((chol>200) && (chol<240)){
            choles="2";
        }else{
            choles="3";
        }

        //glucose conversion
        float sugar=Float.parseFloat(bloodsugar_);
        if(sugar<=11.1){
            gluc="1";
        }else if((sugar>11.1) && (sugar<16.6)){
            gluc="2";
        }else{
            gluc="3";
        }

        //alcohol conversion
        if(alcohol_.equals("Yes")){
            alcoo="1";
        }else{
            alcoo="0";
        }

        //smoke conversion

        if(smoke_.equals("Yes")){
            smok="1";
        }else{
            smok="0";
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // here we are making a post request to the server
                    // here we used StringRequest to define a post request
                // it will take three parameter Request method, url, response listener to hancle the response from server
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // this method process the server response , converting it to JSON object and then extracting output value
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String output=jsonObject.getString("output");

                            Intent intent=new Intent(Profile.this,Prediction.class);
                            intent.putExtra("value",choles);
                            intent.putExtra("result",output);
                            startActivity(intent);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    // getParams is the part of String request Class in volley library , it overriden to provide set of parameters that will
                    // be sent as part of Post request to server
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("age",age.getText().toString());
                        params.put("gender",gen);
                        params.put("systolic bp",systolicbp.getEditText().getText().toString());
                        params.put("diastolic bp",diastolicbp.getEditText().getText().toString());
                        params.put("cholesterol",choles);
                        params.put("gluc",gluc);
                        params.put("smoke",smok);
                        params.put("alco",alcoo);

                        return  params;
                    }
                };

                // here we added the string request in RequestQueue object
                RequestQueue queue = Volley.newRequestQueue(Profile.this);
                queue.add(stringRequest);

            }
        });
    }
}