package com.example.bookmydoctormeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserClinicHospital extends AppCompatActivity {

    String name_of_doctor,telephone,whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_clinic_hospital);

        TextView info1,info2,info3,info4;
        ImageButton call,wa;

        info1 = findViewById(R.id.info1);
        info2 = findViewById(R.id.info2);
        info3 = findViewById(R.id.info3);
        info4 = findViewById(R.id.info4);
        call = findViewById(R.id.call);
        wa = findViewById(R.id.wa);

        Intent intent = getIntent();
        String TELEPHONE = intent.getStringExtra("DT");

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("clinic_hospital");
        Query take_clinic_hospital = reference.orderByChild("telephone").equalTo(TELEPHONE);
        take_clinic_hospital.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    name_of_doctor = snapshot.child(TELEPHONE).child("d_name").getValue(String.class);
                    String name_clinic_hospital = snapshot.child(TELEPHONE).child("name_clinic_hospital").getValue(String.class);
                    String designation_of_doctor = snapshot.child(TELEPHONE).child("designation").getValue(String.class);
                    String specialization_of_doctor = snapshot.child(TELEPHONE).child("specialization").getValue(String.class);
                    String yrsofexp = snapshot.child(TELEPHONE).child("yrsofexp").getValue(String.class);
                    String address_clinic_hospital = snapshot.child(TELEPHONE).child("address_clinic_hospital").getValue(String.class);
                    String timings_clinic_hospital= snapshot.child(TELEPHONE).child("timings_clinic_hospital").getValue(String.class);
                    String website = snapshot.child(TELEPHONE).child("website").getValue(String.class);
                    telephone = snapshot.child(TELEPHONE).child("telephone").getValue(String.class);
                    whatsapp = snapshot.child(TELEPHONE).child("whatsapp").getValue(String.class);

                    String para_for_appointment = name_of_doctor.toUpperCase()+" ("+designation_of_doctor.toUpperCase()+")  is specialized in "+
                            specialization_of_doctor+" practises at "+name_clinic_hospital.toUpperCase()+ " and has an experience of "+yrsofexp+"+ years.";
                    info1.setText(para_for_appointment);
                    info2.setText("Address: "+address_clinic_hospital);
                    info3.setText("Timings: "+timings_clinic_hospital);
                    info4.setText("Website: "+website);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telephone, null));
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(UserClinicHospital.this);
                builder.setMessage("Are you sure you want to call for an appointment?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserClinicHospital.this,BookAppointmentForUser.class);
                intent.putExtra("DOCTORNAME",name_of_doctor);
                intent.putExtra("WHATSAPP",whatsapp);
                startActivity(intent);
            }
        });

    }
}