package com.example.bookmydoctormeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DoctorHome extends AppCompatActivity {

    TextView name_of_doctor;
    Button update;
    EditText address, contact, timings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        name_of_doctor = findViewById(R.id.name_of_doctor);
        Intent intent = getIntent();
        String NAME = intent.getStringExtra("NAME_OF_DOCTOR");
        String PHONE = intent.getStringExtra("PHONE_OF_DOCTOR");
        name_of_doctor.setText(NAME);

        update = findViewById(R.id.update);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
        timings = findViewById(R.id.timings);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("doctor_profile");
        Query take_profile = reference.orderByChild("d_phone").equalTo(PHONE);
        take_profile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String address_of_doctor = snapshot.child(PHONE).child("address").getValue(String.class);
                    String contact_of_doctor = snapshot.child(PHONE).child("contact").getValue(String.class);
                    String timings_of_doctor = snapshot.child(PHONE).child("timings").getValue(String.class);

                    address.setText(address_of_doctor);
                    contact.setText(contact_of_doctor);
                    timings.setText(timings_of_doctor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = address.getText().toString();
                String cont = contact.getText().toString();
                String timi = timings.getText().toString();

                if(cont.length()!=10){
                    Toast.makeText(DoctorHome.this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
                }

                else {
                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("doctor_profile");
                    DoctorProfile df = new DoctorProfile(NAME, PHONE, addr, cont, timi);
                    reference.child(PHONE).setValue(df);
                    Toast.makeText(DoctorHome.this, "Profile updated!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}