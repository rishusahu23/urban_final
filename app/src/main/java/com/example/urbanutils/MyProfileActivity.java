package com.example.urbanutils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileActivity extends AppCompatActivity {

    private TextView nameTextview,emailTextview,phoneTextview;
    private ImageView editButton;
    //DATABASE

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        nameTextview=findViewById(R.id.name_id);
        emailTextview=findViewById(R.id.email_id);
        phoneTextview=findViewById(R.id.phone_number_id);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("users");

        fetchData();

        editButton= findViewById(R.id.edit_profile_id);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProfileActivity.this,ProfileEditActivity.class);
                startActivity(intent);
            }
        });




    }

    private void fetchData() {
        String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference.child(userid).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                           nameTextview.setText(dataSnapshot.child("name").getValue().toString());
                           emailTextview.setText(dataSnapshot.child("email").getValue().toString());
                           phoneTextview.setText(dataSnapshot.child("phoneNumber").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
    }
}
