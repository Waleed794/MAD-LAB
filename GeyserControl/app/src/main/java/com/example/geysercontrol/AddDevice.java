package com.example.geysercontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddDevice extends AppCompatActivity {

    private Button buttonAddDevice;
    private EditText editTextDeviceID;
    private EditText editTextDeviceName;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private DatabaseReference mChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        buttonAddDevice=findViewById(R.id.buttonDeviceAdd);
        editTextDeviceID = findViewById(R.id.editTextDeviceID);
        editTextDeviceName = findViewById(R.id.editTextDeviceName);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        mDatabase = FirebaseDatabase.getInstance();
        mRef= mDatabase.getReference("Users");
        mChild=mRef.child(signInAccount.getId());




        buttonAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID= editTextDeviceID.getText().toString();
                String Name= editTextDeviceName.getText().toString();
                mChild.child(ID).setValue(Name);
                Toast.makeText(AddDevice.this, "Device Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();


            }
        });


    }
}