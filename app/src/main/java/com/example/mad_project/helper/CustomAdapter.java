package com.example.mad_project.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.UpdateActivity;
import com.example.mad_project.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Contact> contactList;

    public CustomAdapter(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //recycling
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_contact_container,parent,false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        //setting of data
        Contact c = contactList.get(position);

        holder.txtID.setText(c.getID()+"");
        holder.txtFullName.setText(c.getFirstName() + " " + c.getLastName());
        holder.txtContactNumber.setText(c.getContactNumber());

        holder.imgEdit.setOnClickListener(v -> {
            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("id", String.valueOf(c.getID()));
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtID, txtFullName, txtContactNumber;
        private ImageView imgTrash, imgEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.txtID);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtContactNumber = itemView.findViewById(R.id.txtContactNumber);

            imgTrash = itemView.findViewById(R.id.imgTrash);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }

}
