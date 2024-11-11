package com.example.mad_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_project.entity.Contact;
import com.example.mad_project.helper.BottomNavigationHelper;
import com.example.mad_project.helper.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtContactNumber;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtContactNumber = findViewById(R.id.txtContactNumber);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {

            String firstName = txtFirstName.getText().toString();
            String lastName = txtLastName.getText().toString();
            String contactNumber = txtContactNumber.getText().toString();

            DatabaseHelper dh = new DatabaseHelper(AddActivity.this);

            Contact c = new Contact(firstName, lastName, contactNumber);
            dh.createContact(c);


        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView);
    }
}