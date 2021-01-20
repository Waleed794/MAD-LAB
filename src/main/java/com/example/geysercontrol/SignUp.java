package com.example.geysercontrol;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    private ListView myListView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private DatabaseReference mChild;
    private ArrayList<String> deviceList=new ArrayList<>();;
    private ArrayList<String> deviceList_key=new ArrayList<>();;
    private Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent Deviceintent= getIntent();
        setContentView(R.layout.activity_sign_up);
        //ArrayList<String> arrayList = (ArrayList<String>) Deviceintent.getSerializableExtra("deviceList");
//        Log.e("check list",arrayList.toString());

        buttonAdd=findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddDevice.class);
                startActivity(intent);
            }
        });

        final ArrayAdapter arrayAdapter = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,deviceList);
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(arrayAdapter);
//         String str = arrayList.toString();
         //Toast.makeText(SignUp.this, str, Toast.LENGTH_SHORT).show();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        mDatabase = FirebaseDatabase.getInstance();
        mRef= mDatabase.getReference("Users");
        mChild=mRef.child(signInAccount.getId());


        mChild.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String str = snapshot.getValue(String.class);
                deviceList.add(str);
                String str_key= snapshot.getKey().toString();
                deviceList_key.add(str_key);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(SignUp.this, deviceList_key.get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Menu.class);
                //String str = deviceList_key.get(position).toString();
                intent.putExtra("deviceID", deviceList_key.get(position));
                startActivity(intent);
                //callIntent(deviceList_key.get(position));

            }
        });
    }

    private void callIntent(String position)
    {
        Intent intent = new Intent (getApplicationContext(),Menu.class);
        intent.putExtra("position",position);
        startActivity(intent);
        finish();



    }



}