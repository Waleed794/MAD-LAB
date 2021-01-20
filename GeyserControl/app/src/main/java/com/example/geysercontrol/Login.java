package com.example.geysercontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private DatabaseReference mChild;
    private ArrayList<String> deviceList=new ArrayList<>();
    private GoogleSignInAccount signInAccount;
    private String str;
    private ArrayList newList;

    @Override
    protected void onStart() {


        super.onStart();

        FirebaseUser user=  mAuth.getCurrentUser();


        //uncomment following code if you want to enable auto login
       /* if (user!=null)
        {
            Intent intent = new Intent(getApplicationContext(),Menu.class);
            startActivity(intent);
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        createRequest();
        //mDatabase = FirebaseDatabase.getInstance();

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Toast.makeText(this,"Login Successful !",Toast.LENGTH_LONG).show();

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                           /*  mDatabase = FirebaseDatabase.getInstance();
                             mRef= mDatabase.getReference("Users");*/
                             //GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
                            //GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
                            //mChild=mRef.child()

                            //getList();


                            Intent Deviceintent = new Intent(getApplicationContext(),SignUp.class);
                           // ArrayList<String> list=getList();
                           // String str = list.toString();
                           // Toast.makeText(Login.this, str, Toast.LENGTH_SHORT).show();
                            //Deviceintent.putStringArrayListExtra("deviceList",list);



                            startActivity(Deviceintent);
                            
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Error: Could Not Authenticate", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

   /* public ArrayList<String> getList()
    {
        //deviceList = new ArrayList<>();

        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        mDatabase = FirebaseDatabase.getInstance();
        mRef= mDatabase.getReference("Users");
        mChild=mRef.child(signInAccount.getId());
        //newList= new ArrayList();
        deviceList.add("hello world");


        mChild.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                *//*if (snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        str=dataSnapshot.getValue(String.class);

                        deviceList.add(str);
                    }
                    addToList(deviceList);
                    ArrayList<String> newList = new ArrayList<>(deviceList);

                    Toast.makeText(Login.this, deviceList.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("new List",newList.toString());
                }*//*
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Toast.makeText(Login.this, deviceList.toString(), Toast.LENGTH_SHORT).show();
        Log.e("new List",deviceList.toString());
        return deviceList;
*/
        // tried returning "newList", still causing same issue









}