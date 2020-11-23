package com.example.geysercontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

    public void btn_login(View view) {
        startActivity(new Intent(getApplicationContext(),Menu.class));
    }
}