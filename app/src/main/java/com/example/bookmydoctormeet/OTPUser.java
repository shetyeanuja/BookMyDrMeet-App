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

public class OTPUser extends AppCompatActivity {

    EditText otp_user;
    CheckBox checkBox;
    Button verify_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp_user = findViewById(R.id.otp_both);
        checkBox = findViewById(R.id.checkBox);
        verify_user = findViewById(R.id.verify_both);

        verify_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String u_name = intent.getStringExtra("u_name");
                String u_phone = intent.getStringExtra("u_phone");
                String u_pswd = intent.getStringExtra("u_pswd");
                String u_otp = intent.getStringExtra("u_otp");

                    if(u_otp.equals(otp_user.getText().toString())) {
                        if(checkBox.isChecked()) {
                            Toast.makeText(OTPUser.this, "Phone number verified!", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                            DatabaseReference reference = rootNode.getReference("user");
                            User u = new User(u_name, u_phone, u_pswd);
                            reference.child(u_phone).setValue(u);
                            Toast.makeText(OTPUser.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent1= new Intent(OTPUser.this, Login.class);
                            startActivity(intent1);
                        }
                        else {
                            Toast.makeText(OTPUser.this, "Please agree to the terms and conditions!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(OTPUser.this, "Incorrect OTP!", Toast.LENGTH_SHORT).show();
                    }
                }

        });

    }
}

