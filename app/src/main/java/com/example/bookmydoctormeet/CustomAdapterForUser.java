package com.example.bookmydoctormeet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapterForUser extends RecyclerView.Adapter<CustomAdapterForUser.ViewHolder>{

    Context context;
    ArrayList<DoctorProfile> doctor_info;

    public CustomAdapterForUser(Context context, ArrayList<DoctorProfile> doctor_info) {
        this.context = context;
        this.doctor_info = doctor_info;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<DoctorProfile> filtered_doctor_info){
        doctor_info = filtered_doctor_info;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, addressD, contactD, timingsD;
        ImageButton imageButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            doctorName = view.findViewById(R.id.doctorName);
            addressD = view.findViewById(R.id.contactD);
            contactD = view.findViewById(R.id.addressD);
            timingsD = view.findViewById(R.id.timingsD);
            imageButton = view.findViewById(R.id.imageButton);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_for_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DoctorProfile dp = doctor_info.get(position);
        viewHolder.doctorName.setText(dp.getD_name());
        viewHolder.addressD.setText("Address- "+dp.getAddress());
        viewHolder.contactD.setText("Contact- "+dp.getContact());
        viewHolder.timingsD.setText("Timings- "+dp.getTimings());

        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookAppointmentForUser.class);
                String dname = viewHolder.doctorName.getText().toString();
                String dcontact = viewHolder.contactD.getText().toString();
                intent.putExtra("DN",dname);
                intent.putExtra("DP",dcontact);
                context.startActivity(intent);
            }
        });



    }


    @Override
    public int getItemCount() {
        return(doctor_info.size());
    }
}