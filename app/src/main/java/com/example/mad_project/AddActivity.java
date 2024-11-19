package com.example.mad_project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_project.entity.Contact;
import com.example.mad_project.helper.BottomNavigationHelper;
import com.example.mad_project.helper.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtContactNumber;
    Button btnAdd, btnDialogConfirm;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this, SystemBarStyle.dark(android.graphics.Color.TRANSPARENT));


        setContentView(R.layout.activity_add);

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtContactNumber = findViewById(R.id.txtContactNumber);

        dialog = new Dialog(AddActivity.this);
        dialog.setContentView(R.layout.added_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        dialog.setCancelable(false);

        btnDialogConfirm = dialog.findViewById(R.id.btnDialogConfirm);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {

            String firstName = txtFirstName.getText().toString();
            String lastName = txtLastName.getText().toString();
            String contactNumber = txtContactNumber.getText().toString();


            if((contactNumber.length()==11&&contactNumber.startsWith("0"))||((contactNumber.length()>=11&&contactNumber.length()<=14)&&contactNumber.startsWith("+"))){
                DatabaseHelper dh = new DatabaseHelper(AddActivity.this);

                Contact c = new Contact(firstName, lastName, contactNumber);
                dh.createContact(c);

                dialog.show();
            }else{
                Toast.makeText(this, "Number must start with '+' and be between 11 to 14 digits or start with '0' and have a total of 11 digits", Toast.LENGTH_LONG).show();
            }

        });

        btnDialogConfirm.setOnClickListener(v -> {
            Intent i = new Intent(AddActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
            dialog.dismiss();
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView);
    }
}