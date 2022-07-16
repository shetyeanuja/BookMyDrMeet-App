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
    Button done,practise;
    EditText designation,specialization,yrsofexp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        name_of_doctor = findViewById(R.id.name_of_doctor);
        Intent intent = getIntent();
        String NAME = intent.getStringExtra("NAME_OF_DOCTOR");
        String PHONE = intent.getStringExtra("PHONE_OF_DOCTOR");
        name_of_doctor.setText(NAME.toUpperCase());

        done = findViewById(R.id.done);
        practise = findViewById(R.id.practise);
        designation = findViewById(R.id.designation);
        specialization = findViewById(R.id.specialization);
        yrsofexp = findViewById(R.id.yrsofexp);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("doctor_profile");
        Query take_profile = reference.orderByChild("d_phone").equalTo(PHONE);
        take_profile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String designation_of_doctor = snapshot.child(PHONE).child("designation").getValue(String.class);
                    String specialization_of_doctor = snapshot.child(PHONE).child("specialization").getValue(String.class);
                    String yrsofexp_of_doctor = snapshot.child(PHONE).child("yrsofexp").getValue(String.class);

                    designation.setText(designation_of_doctor);
                    specialization.setText(specialization_of_doctor);
                    yrsofexp.setText(yrsofexp_of_doctor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String designation_of_doctor = designation.getText().toString();
                String specialization_of_doctor = specialization.getText().toString();
                String yrsofexp_of_doctor = yrsofexp.getText().toString();

                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("doctor_profile");
                DoctorProfile df = new DoctorProfile(NAME, PHONE, designation_of_doctor, specialization_of_doctor, yrsofexp_of_doctor);
                reference.child(PHONE).setValue(df);
                Toast.makeText(DoctorHome.this, "Profile updated!", Toast.LENGTH_SHORT).show();


            }
        });

        practise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHome.this, Locations.class);
                intent.putExtra("NAME_OF_DOCTOR", NAME);
                intent.putExtra("PHONE_OF_DOCTOR", PHONE);
                intent.putExtra("DESIGNATION", designation.getText().toString());
                intent.putExtra("SPECIALIZATION", specialization.getText().toString());
                intent.putExtra("YEARS", yrsofexp.getText().toString());
                startActivity(intent);
            }
        });

    }
}