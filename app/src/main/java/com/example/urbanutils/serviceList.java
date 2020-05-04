package com.example.urbanutils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class serviceList extends AppCompatActivity {
    TextView title_profession;
    ListView listOfService;
    FirebaseDatabase database;
    DatabaseReference Ref;
    ArrayList<Provider> serviceList = new ArrayList<Provider>();
    MyCustomAdapter myadapter;
    Provider provider;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service_list);
            Bundle bundle=getIntent().getExtras();
            String profession = bundle.getString("profession");
            title_profession=(TextView)findViewById(R.id.title_profession);
            provider = new Provider();
            listOfService = (ListView) findViewById(R.id.listOfService);
            database = FirebaseDatabase.getInstance();
            Ref = database.getReference("providers");
            title_profession.setText(profession);

         Ref.child(profession).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    provider = ds.getValue(Provider.class);
                    String name = provider.getName();
                    String address = provider.getAddress();
                    String businessType = provider.getBusinessType();
                    String email = provider.getEmail();
                    String phoneNumber= provider.getPhoneNumber();
                    double lattitude = provider.getLattitude();
                    double longitude = provider.getLongitude();
                    boolean approval = provider.isApproval();

                    if(approval== true)
                        serviceList.add(new Provider(name,address,businessType,email,lattitude,longitude,phoneNumber,approval));
                }
                myadapter = new MyCustomAdapter(serviceList);
                listOfService.setAdapter(myadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public class MyCustomAdapter extends BaseAdapter {
        public ArrayList<Provider> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<Provider> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;

        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.itemsview, null);


            final Provider s = listnewsDataAdpater.get(position);

            TextView tvTitle = (TextView) myView.findViewById(R.id.tvtitle);
            tvTitle.setText(s.getName());
            TextView tvtitle2=(TextView)myView.findViewById(R.id.tvtitle2);
            tvtitle2.setText(s.getAddress());
            TextView prof =(TextView)myView.findViewById(R.id.prof);

            prof.setText(s.getBusinessType());


            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), customerService.class);
                    intent.putExtra("providercustomer",  s);
                    startActivity(intent);
                    finish();


                }
            });

            return myView;
        }

    }



}

