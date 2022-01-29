package com.example.bookmydoctormeet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText login_phone, login_pswd;
    Button login_submit, forgot_pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_phone = findViewById(R.id.login_phone);
        login_pswd = findViewById(R.id.login_pswd);
        login_submit = findViewById(R.id.login_submit);
        forgot_pswd = findViewById(R.id.forgot_pswd);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(login_phone.getText())) {
                    login_phone.setError("Phone no.");
                    }
                else if(TextUtils.isEmpty(login_pswd.getText())) {
                    login_pswd.setError("Password");
                    }
                else {
                    String l_phone = login_phone.getText().toString();
                    String l_pswd = login_pswd.getText().toString();

                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("user");
                    Query verify_user = reference.orderByChild("u_phone").equalTo(l_phone);
                    verify_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String passwordFromDB = snapshot.child(l_phone).child("u_pswd").getValue(String.class);
                                if (passwordFromDB.equals(l_pswd)) {
                                    Toast.makeText(Login.this, "User Login Successful!", Toast.LENGTH_SHORT).show();
                                    String name_of_user = snapshot.child(l_phone).child("u_name").getValue(String.class);
                                    Intent intent = new Intent(Login.this, UserHome.class);
                                    intent.putExtra("NAME_OF_USER", name_of_user);
                                    intent.putExtra("PHONE_OF_USER", l_phone);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                DatabaseReference reference = rootNode.getReference("doctor");
                                Query verify_doctor = reference.orderByChild("d_phone").equalTo(l_phone);
                                verify_doctor.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String passwordFromDB = snapshot.child(l_phone).child("d_pswd").getValue(String.class);
                                            if (passwordFromDB.equals(l_pswd)) {
                                                Toast.makeText(Login.this, "Doctor Login Successful!", Toast.LENGTH_SHORT).show();
                                                String name_of_doctor = snapshot.child(l_phone).child("d_name").getValue(String.class);
                                                Intent intent = new Intent(Login.this, DoctorHome.class);
                                                intent.putExtra("NAME_OF_DOCTOR", name_of_doctor);
                                                intent.putExtra("PHONE_OF_DOCTOR", l_phone);
                                                startActivity(intent);
                                            }
                                            else {
                                                Toast.makeText(Login.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                        else {
                                            Toast.makeText(Login.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    
                }

            }
    });


        forgot_pswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(Login.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                {
                    try
                    {
                        String phone_for_pswd = login_phone.getText().toString();
                        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                        DatabaseReference reference = rootNode.getReference("user");
                        Query is_user = reference.orderByChild("u_phone").equalTo(phone_for_pswd);
                        is_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String pswd = snapshot.child(phone_for_pswd).child("u_pswd").getValue(String.class);
                                    String smsMgrVar = "The password for phone number: " + phone_for_pswd + " is: " + pswd + " .Regards MediSeen!";
                                    SmsManager sms = SmsManager.getDefault();
                                    sms.sendTextMessage(phone_for_pswd, "9004165540", smsMgrVar, null, null);
                                    Toast.makeText(Login.this, "Password sent to the registered phone number", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                    DatabaseReference reference = rootNode.getReference("doctor");
                                    Query is_doctor = reference.orderByChild("d_phone").equalTo(phone_for_pswd);
                                    is_doctor.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                String pswd = snapshot.child(phone_for_pswd).child("d_pswd").getValue(String.class);
                                                String smsMgrVar = "The password for phone number: " + phone_for_pswd + " is: " + pswd + " Regards MediSeen!";
                                                SmsManager sms = SmsManager.getDefault();
                                                sms.sendTextMessage(phone_for_pswd, null, smsMgrVar, null, null);
                                                Toast.makeText(Login.this, "Password sent to the registered phone number", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Login.this, "Phone number does not exists!", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }


                        });


                    }


                    catch(Exception ErrVar)
                    {
                        Toast.makeText(Login.this,"Some error occurred", Toast.LENGTH_LONG).show();
                        ErrVar.printStackTrace();
                    }


                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
                    }
                }

            }
        });

    }
}





