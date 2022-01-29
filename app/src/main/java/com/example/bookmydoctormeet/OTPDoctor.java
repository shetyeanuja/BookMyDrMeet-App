package com.example.bookmydoctormeet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OTPDoctor extends AppCompatActivity {

    EditText otp_doctor;
    CheckBox checkBox2;
    Button verify_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpdoctor);

        otp_doctor = findViewById(R.id.otp_doctor);
        checkBox2 = findViewById(R.id.checkBox2);
        verify_doctor = findViewById(R.id.verify_doctor);

        verify_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String d_name = intent.getStringExtra("d_name");
                String d_phone = intent.getStringExtra("d_phone");
                String d_pswd = intent.getStringExtra("d_pswd");
                String d_regno = intent.getStringExtra("d_regno");
                String d_otp = intent.getStringExtra("d_otp");

                if(d_otp.equals(otp_doctor.getText().toString())) {
                    if(checkBox2.isChecked()) {
                        Toast.makeText(OTPDoctor.this, "Phone number verified!", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                        DatabaseReference reference = rootNode.getReference("doctor");
                        Doctor d = new Doctor(d_name, d_phone, d_pswd, d_regno);
                        reference.child(d_phone).setValue(d);
                        FirebaseDatabase rootNode1 = FirebaseDatabase.getInstance();
                        DatabaseReference reference1 = rootNode1.getReference("doctor_profile");
                        DoctorProfile dp = new DoctorProfile(d_name,d_phone,"","","");
                        reference1.child(d_phone).setValue(dp);
                        Toast.makeText(OTPDoctor.this, "Doctor registered successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent1= new Intent(OTPDoctor.this, Login.class);
                        startActivity(intent1);
                    }
                    else {
                        Toast.makeText(OTPDoctor.this, "Please agree to the terms and conditions!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(OTPDoctor.this, "Incorrect OTP!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}

