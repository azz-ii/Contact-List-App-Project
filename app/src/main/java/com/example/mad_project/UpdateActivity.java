package com.example.mad_project;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mad_project.entity.Contact;
import com.example.mad_project.helper.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    TextView lblID;
    EditText txtFirstName, txtLastName, txtContactNumber;
    Dialog dialog;
    Button btnUpdate, btnCancel, btnDialogConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        EdgeToEdge.enable(this, SystemBarStyle.dark(android.graphics.Color.TRANSPARENT));

        lblID = findViewById(R.id.lblID);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtContactNumber = findViewById(R.id.txtContactNumber);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        dialog = new Dialog(UpdateActivity.this);
        dialog.setContentView(R.layout.updated_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        dialog.setCancelable(false);

        btnDialogConfirm = dialog.findViewById(R.id.btnDialogConfirm);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        lblID.setText("Current ID: " + id);

        DatabaseHelper dh = new DatabaseHelper(UpdateActivity.this);
        Cursor cursor = dh.readContactByID(id);

        if (cursor.moveToFirst()){
            txtFirstName.setText(cursor.getString(1));
            txtLastName.setText(cursor.getString(2));
            txtContactNumber.setText(cursor.getString(3));
        }

        btnCancel.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

        btnUpdate.setOnClickListener(v -> {

            int ID = Integer.parseInt(id);
            String firstName = txtFirstName.getText().toString();
            String lastName = txtLastName.getText().toString();
            String contactNumber = txtContactNumber.getText().toString();

            if((contactNumber.length()==11&&contactNumber.startsWith("0"))||(contactNumber.length()==13&&contactNumber.startsWith("+63"))){
                Contact c = new Contact(ID, firstName, lastName, contactNumber);
                long result = dh.updateContactByID(c);

                if (result == -1){
                    Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "UPDATE SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    btnDialogConfirm.setOnClickListener(v1 -> {
                        dialog.dismiss();
                        getOnBackPressedDispatcher().onBackPressed();
                    });

                }
                dialog.show();
            }else{
                Toast.makeText(this, "Number must start with +63 and 11 digits or start with 0 and 13 digits", Toast.LENGTH_LONG).show();
            }
        });




    }
}