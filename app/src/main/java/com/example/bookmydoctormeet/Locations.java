package com.example.bookmydoctormeet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Locations extends AppCompatActivity {

    Button back_doctor_home,save_next;
    TextView add_name;
    EditText name_clinic_hospital,telephone,whatsapp,address_clinic_hospital,timings_clinic_hospital,website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Intent intent = getIntent();
        String NAME = intent.getStringExtra("NAME_OF_DOCTOR");
        String PHONE = intent.getStringExtra("PHONE_OF_DOCTOR");
        String DESIG = intent.getStringExtra("DESIGNATION");
        String SPEC = intent.getStringExtra("SPECIALIZATION");
        String YRS = intent.getStringExtra("YEARS");

        name_clinic_hospital = findViewById(R.id.name_clinic_hospital);
        telephone = findViewById(R.id.telephone);
        whatsapp = findViewById(R.id.whatsapp);
        address_clinic_hospital = findViewById(R.id.address_clinic_hospital);
        timings_clinic_hospital = findViewById(R.id.timings_clinic_hospital);
        website = findViewById(R.id.website);
        add_name = findViewById(R.id.add_name);
        back_doctor_home = findViewById(R.id.back_doctor_home);
        save_next = findViewById(R.id.save_next);


        back_doctor_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Locations.this, DoctorHome.class);
                intent.putExtra("NAME_OF_DOCTOR", NAME);
                intent.putExtra("PHONE_OF_DOCTOR", PHONE);
                startActivity(intent);
            }
        });

        save_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name_clinic_hospital.getText())) {
                    name_clinic_hospital.setError("Please enter the name");
                }
                else if(TextUtils.isEmpty(telephone.getText())) {
                    telephone.setError("Please enter the phone number");
                }

                else {

                    String name = name_clinic_hospital.getText().toString();
                    String tele = telephone.getText().toString();
                    String wa = whatsapp.getText().toString();
                    String addr = address_clinic_hospital.getText().toString();
                    String timi = timings_clinic_hospital.getText().toString();
                    String web = website.getText().toString();

                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("clinic_hospital");
                    DoctorClinicHospital dch = new DoctorClinicHospital(NAME,PHONE,DESIG,SPEC,YRS,name,tele, wa,addr,timi,web);
                    reference.child(tele).setValue(dch);
                    Toast.makeText(Locations.this, "Added Successfully!", Toast.LENGTH_SHORT).show();

                    name_clinic_hospital.setText("");
                    telephone.setText("");
                    whatsapp.setText("");
                    address_clinic_hospital.setText("");
                    timings_clinic_hospital.setText("");
                    website.setText("");

                }

            }
        });
    }


}