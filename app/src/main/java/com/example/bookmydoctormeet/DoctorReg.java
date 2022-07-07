package com.example.bookmydoctormeet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Random;

public class DoctorReg extends AppCompatActivity {

    EditText d_name, d_phone, d_regno, d_set_pswd, d_confirm_pswd;
    Button d_submit;
    Random random = new Random();
    int x = random.nextInt(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_reg);

        d_name = findViewById(R.id.d_name);
        d_phone = findViewById(R.id.d_phone);
        d_regno = findViewById(R.id.d_regno);
        d_set_pswd = findViewById(R.id.d_set_pswd);
        d_confirm_pswd = findViewById(R.id.d_confirm_pswd);
        d_submit = findViewById(R.id.d_submit);

        d_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(d_name.getText())) {
                    d_name.setError("Name");
                }
                else if(TextUtils.isEmpty(d_phone.getText())) {
                    d_phone.setError("Phone");
                }
                else if(TextUtils.isEmpty(d_regno.getText())) {
                    d_regno.setError("Registration no.");
                }
                else if(TextUtils.isEmpty(d_set_pswd.getText())) {
                    d_set_pswd.setError("Set a password");
                }
                else if(TextUtils.isEmpty(d_confirm_pswd.getText())) {
                    d_confirm_pswd.setError("Confirm the set password");
                }
                else {
                    String doctor_name = d_name.getText().toString();
                    String doctor_phone = d_phone.getText().toString();
                    String doctor_regno = d_regno.getText().toString();
                    String set_p = d_set_pswd.getText().toString();
                    String confirm_p = d_confirm_pswd.getText().toString();

                    if(doctor_phone.length()!=10){
                        Toast.makeText(DoctorReg.this, "Incorrect phone number!", Toast.LENGTH_SHORT).show();
                    }
                    else if(!set_p.equals(confirm_p)){
                        Toast.makeText(DoctorReg.this, "Confirm Password does not match Set Password!", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        if (ActivityCompat.checkSelfPermission(DoctorReg.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            try {
                                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                DatabaseReference reference = rootNode.getReference("doctor");
                                Query q1 = reference.orderByChild("u_phone").equalTo(doctor_phone);
                                q1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(!snapshot.exists()){
                                            String x1 = String.valueOf(x);
                                            String smsMgrVar = "Hey "+doctor_name.toUpperCase()+"! Thanks for registering. The OTP for your phone number: " + doctor_phone + " is: " + x1 + " .Regards BookMyDrMeet!";
                                            SmsManager sms = SmsManager.getDefault();
                                            sms.sendTextMessage(doctor_phone, "8169001942", smsMgrVar, null, null);
                                            Toast.makeText(DoctorReg.this, "OTP sent to your phone!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(DoctorReg.this,OTPDoctor.class);
                                            intent.putExtra("d_name",doctor_name);
                                            intent.putExtra("d_phone",doctor_phone);
                                            intent.putExtra("d_pswd",set_p);
                                            intent.putExtra("d_regno",doctor_regno);
                                            intent.putExtra("d_otp",x1);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(DoctorReg.this, "Phone exists. Please login with your phone!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            catch (Exception ErrVar) {
                                Toast.makeText(DoctorReg.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                                ErrVar.printStackTrace();
                            }
                        }

                        else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
                            }
                        }

                    }

                }
            }
        });

    }
}


