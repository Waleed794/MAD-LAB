package com.example.geysercontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    TextView name,mail;
    Button btn_logout;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private DatabaseReference mChild;
    DeviceInfo deviceInfo = new DeviceInfo();
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private boolean radioBool=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String deviceID=intent.getStringExtra("deviceID");

        btn_logout=findViewById(R.id.button8);
        name=findViewById(R.id.textView11);
        mail=findViewById(R.id.textView12);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);


        mDatabase = FirebaseDatabase.getInstance();
        mRef= mDatabase.getReference("Devices");
        mChild=mRef.child(deviceID);
        mChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
                viewData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //String userID= signInAccount.getId();
        //String userName= signInAccount.getDisplayName();
        //mRef.child(userID).setValue(userName);
        /*mRef.child(userID).child("device1").setValue(true);
        mRef.child(userID).child("device2").setValue(true);*/



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
       // radioButton = findViewById(R.id.radio_hot);
        radioGroup=findViewById(R.id.radioGroup);

    }


    private void viewData() {
        name.setText(deviceInfo.getMotor());
        mail.setText(deviceInfo.getTemp());
    }

    private void showData(DataSnapshot snapshot) {


            deviceInfo.setMotor(snapshot.child("Motor").getValue(String.class));
            deviceInfo.setTemp(snapshot.child("Temp").getValue(String.class));
            String str = deviceInfo.getMotor();
            radioInit(deviceInfo.getMotor());

         /*   Log.e("Motor",str);*/


    }

    public void btn_SetSchedule(View view) {
        startActivity(new Intent(getApplicationContext(),schedule.class));
    }


    public void radioClick(View view) {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Log.e("radio id: ",String.valueOf(radioId));
        switch (radioId)
        {
            case R.id.radio_Warm:
                Toast.makeText(this, "Task Successful", Toast.LENGTH_SHORT).show();
                mChild.child("Motor").setValue("1");
                break;

            case R.id.radio_Hot:
                Toast.makeText(this, "Task Successful", Toast.LENGTH_SHORT).show();
                mChild.child("Motor").setValue("2");
                break;

            case R.id.radio_veryhot:
                Toast.makeText(this, "Task Successful", Toast.LENGTH_SHORT).show();
                mChild.child("Motor").setValue("3");
                break;

        }
    }

    private void radioInit(String motor)
    {
        if(radioBool)
        {
            radioBool=false;
            switch (motor)
            {
                case "1":
                    radioButton=findViewById(R.id.radio_Warm);
                    radioButton.setChecked(true);
                    break;
                case "2":
                    radioButton=findViewById(R.id.radio_Hot);
                    radioButton.setChecked(true);
                    break;
                case "3":
                    radioButton=findViewById(R.id.radio_veryhot);
                    radioButton.setChecked(true);
                    break;
            }
        }

    }
}