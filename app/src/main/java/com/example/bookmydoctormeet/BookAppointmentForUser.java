package com.example.bookmydoctormeet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BookAppointmentForUser extends AppCompatActivity {

    EditText patient_name, patient_age;
    Button send;
    RadioGroup radioGroup;
    RadioButton male, female, others, radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment_for_user);

        Intent intent = getIntent();
        String doctorname = intent.getStringExtra("DOCTORNAME");
        String whatsapp = intent.getStringExtra("WHATSAPP");

        send = findViewById(R.id.send);
        patient_age = findViewById(R.id.patient_age);
        patient_name = findViewById(R.id.patient_name);
        radioGroup = findViewById(R.id.radioGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        others = findViewById(R.id.others);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(patient_name.getText())) {
                    patient_name.setError("?");
                }
                else if(TextUtils.isEmpty(patient_age.getText())) {
                    patient_age.setError("?");
                }
                else {

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    String p_name = patient_name.getText().toString();
                                    String p_age = patient_age.getText().toString();
                                    int selectedId = radioGroup.getCheckedRadioButtonId();
                                    radioButton = (RadioButton) findViewById(selectedId);
                                    String p_gender = radioButton.getText().toString();

                                    String msg = "Hello "+doctorname.toUpperCase()+" Patient Name: "+p_name.toUpperCase()+", Patient Age: "+p_age+" , Patient Gender: "+p_gender.toUpperCase()+ "," +
                                            " I am requesting for an appointment from BookMyDrMeet!";

                                    try {
                                        String mobile = "91"+whatsapp;
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
                                    }catch (Exception e){
                                        Toast.makeText(BookAppointmentForUser.this,"Whatsapp not installed!",Toast.LENGTH_SHORT).show();
                                    }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(BookAppointmentForUser.this);
                    builder.setMessage("Are you sure you want to send WhatsApp for an appointment?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }

            }
        });

    }
}