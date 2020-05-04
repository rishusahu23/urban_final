package com.example.urbanutils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProviderLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText professionEdittext,mobileEdittext;
    private Button loginButton;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_login);

        initialiseUI();

        loginButton.setOnClickListener(this);


    }

    private void initialiseUI() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        professionEdittext=findViewById(R.id.profession_id);
        mobileEdittext=findViewById(R.id.mobile_id);
        loginButton=findViewById(R.id.login_id);


    }

    @Override
    public void onClick(View v) {
        String profession=professionEdittext.getText().toString();
        String mobile=mobileEdittext.getText().toString();
        String newMobile="+91"+mobile;

        databaseReference.child("providers").child(profession).child(newMobile).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(ProviderLoginActivity.this, "key exist", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ProviderLoginActivity.this, ShowRequestedUserActivity.class);
                    intent.putExtra("PROVIDERPROFESSION",profession);
                    intent.putExtra("MOBILE",newMobile);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ProviderLoginActivity.this, "error1", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProviderLoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
