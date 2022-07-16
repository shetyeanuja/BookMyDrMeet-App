package com.example.bookmydoctormeet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapterForUser extends RecyclerView.Adapter<CustomAdapterForUser.ViewHolder>{

    Context context;
    ArrayList<DoctorClinicHospital> doctor_info;

    public CustomAdapterForUser(Context context, ArrayList<DoctorClinicHospital> doctor_info) {
        this.context = context;
        this.doctor_info = doctor_info;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<DoctorClinicHospital> filtered_doctor_info){
        doctor_info = filtered_doctor_info;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_of_cliniHosp, name_doc_desig;
        Button more;

        public ViewHolder(@NonNull View view) {
            super(view);
            name_of_cliniHosp = view.findViewById(R.id.name_of_cliniHosp);
            name_doc_desig = view.findViewById(R.id.name_doc_desig);
            more = view.findViewById(R.id.more);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_for_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DoctorClinicHospital dp = doctor_info.get(position);
        viewHolder.name_of_cliniHosp.setText(dp.getName_clinic_hospital().toUpperCase());
        viewHolder.name_doc_desig.setText(dp.getD_name().toUpperCase()+" ("+dp.getDesignation().toUpperCase()+")");

        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UserClinicHospital.class);
                String doctor_telephone = dp.getTelephone();
                intent.putExtra("DT",doctor_telephone);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return(doctor_info.size());
    }
}