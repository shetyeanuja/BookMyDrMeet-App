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

public class UserReg extends AppCompatActivity {

    EditText u_name, u_phone, u_set_pswd, u_confirm_pswd;
    Button u_submit;
    Random random = new Random();
    int x = random.nextInt(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg);

        u_name = findViewById(R.id.u_name);
        u_phone = findViewById(R.id.u_phone);
        u_set_pswd = findViewById(R.id.u_set_pswd);
        u_confirm_pswd = findViewById(R.id.u_confirm_pswd);
        u_submit = findViewById(R.id.u_submit);

        u_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(u_name.getText())) {
                    u_name.setError("Name");
                }
                if(TextUtils.isEmpty(u_phone.getText())) {
                    u_phone.setError("Phone");
                }
                else if(TextUtils.isEmpty(u_set_pswd.getText())) {
                    u_set_pswd.setError("Set a password");
                }
                else if(TextUtils.isEmpty(u_confirm_pswd.getText())) {
                    u_confirm_pswd.setError("Confirm the set password");
                }
                else {
                    String user_name = u_name.getText().toString();
                    String user_phone = u_phone.getText().toString();
                    String set_p = u_set_pswd.getText().toString();
                    String confirm_p = u_confirm_pswd.getText().toString();

                    if(user_phone.length()!=10){
                        Toast.makeText(UserReg.this, "Incorrect phone number!", Toast.LENGTH_SHORT).show();
                    }
                    else if(!set_p.equals(confirm_p)){
                        Toast.makeText(UserReg.this, "Confirm Password does not match Set Password!", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        if (ActivityCompat.checkSelfPermission(UserReg.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            try {
                                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                DatabaseReference reference = rootNode.getReference("user");
                                Query q1 = reference.orderByChild("u_phone").equalTo(user_phone);
                                q1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(!snapshot.exists()){
                                            String x1 = String.valueOf(x);
                                            String smsMgrVar = "Hey "+user_name.toUpperCase()+"! Thanks for registering. The OTP for your phone number: " + user_phone + " is: " + x1 + " .Regards BookMyDrMeet!";
                                            SmsManager sms = SmsManager.getDefault();
                                            sms.sendTextMessage(user_phone, "8169001942", smsMgrVar, null, null);
                                            Toast.makeText(UserReg.this, "OTP sent to your phone!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(UserReg.this,OTPUser.class);
                                            intent.putExtra("u_name",user_name);
                                            intent.putExtra("u_phone",user_phone);
                                            intent.putExtra("u_pswd",set_p);
                                            intent.putExtra("u_otp",x1);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(UserReg.this, "Phone exists. Please login with your phone!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            catch (Exception ErrVar) {
                                Toast.makeText(UserReg.this, "Some error occurred", Toast.LENGTH_SHORT).show();
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



