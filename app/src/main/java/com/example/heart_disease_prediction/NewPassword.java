package com.example.heart_disease_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPassword extends AppCompatActivity {

    TextInputLayout newpassword,confirm_password;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        newpassword=findViewById(R.id.newpassword);
        confirm_password=findViewById(R.id.confirmpassword);

        updatebtn=findViewById(R.id.update);

    }

    private Boolean validateNewPassword(){
        String password=newpassword.getEditText().getText().toString();
        if(password.isEmpty()){
            newpassword.setError("Fields cannot be empty");
            return false;
        }else{
            newpassword.setError(null);
            newpassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmPassword(){
        String password=newpassword.getEditText().getText().toString();
        String confirmpassword=confirm_password.getEditText().getText().toString();
        if(confirmpassword.isEmpty()){
            confirm_password.setError("Fields cannot be empty");
            return false;
        }else if(!password.equals(confirmpassword)){
            confirm_password.setError("password not matched");
            return false;
        }
        else{
            confirm_password.setError(null);
            confirm_password.setErrorEnabled(false);
            return true;
        }
    }

    public void updatePassword(View view){
        if(!validateNewPassword() | !validateConfirmPassword() ){
            return;
        }
        else{
            String _password=newpassword.getEditText().getText().toString();
            String number=getIntent().getStringExtra("phonenumber");

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
            reference.child(number).child("Password").setValue(_password);

            Intent intent=new Intent(NewPassword.this,PasswordChangedSuccess.class);
            startActivity(intent);
        }
    }
}