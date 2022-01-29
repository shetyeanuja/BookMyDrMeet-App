package com.example.bookmydoctormeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class UserHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DoctorProfile> doctor_info;
    CustomAdapterForUser customAdapterForUser;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView userName;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        userName = findViewById(R.id.userName);
        searchView = findViewById(R.id.searchView);

        Intent intent = getIntent();
        String NAME = intent.getStringExtra("NAME_OF_USER");
        String PHONE = intent.getStringExtra("PHONE_OF_USER");

        Intent intent1 = new Intent(UserHome.this, BookAppointmentForUser.class);
        intent1.putExtra("UN",NAME);
        intent1.putExtra("UP",PHONE);

        userName.setText("USER: "+NAME);

        recyclerView = findViewById(R.id.recyclerView);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("doctor_profile");

        recyclerView.setLayoutManager(new LinearLayoutManager(UserHome.this));

        doctor_info = new ArrayList<>();

        customAdapterForUser = new CustomAdapterForUser(UserHome.this,doctor_info);
        recyclerView.setAdapter(customAdapterForUser);

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DoctorProfile dp = dataSnapshot.getValue(DoctorProfile.class);
                    doctor_info.add(dp);
                }
                customAdapterForUser.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });

    }
    public void filter(String s){
        ArrayList<DoctorProfile> filtered_doctor_info = new ArrayList<>();
        for (DoctorProfile item : doctor_info) {
            if (item.getAddress().toLowerCase().contains(s.toLowerCase())) {
                filtered_doctor_info.add(item);
            }
        }
        if (filtered_doctor_info.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        else {
            customAdapterForUser.filterList(filtered_doctor_info);
        }

    }

}