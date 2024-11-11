package com.example.mad_project;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.entity.Contact;
import com.example.mad_project.helper.BottomNavigationHelper;
import com.example.mad_project.helper.CustomAdapter;
import com.example.mad_project.helper.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    ArrayList<Contact> contactList = new ArrayList<>();
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView);

        displayContacts();
    }

    void displayContacts(){

        contactList.clear();
        DatabaseHelper dh = new DatabaseHelper(HomeActivity.this);
        Cursor cur = dh.readAllContacts();

        if(cur.getCount() == 0){
            //NO DATA
        }else {
            while(cur.moveToNext()){
                Contact c = new Contact(
                        cur.getInt(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3)
                );
                contactList.add(c);

            }
            Toast.makeText(this, contactList.size() + "", Toast.LENGTH_SHORT).show();
        }

        rvContacts = findViewById(R.id.rvContacts);
        //context is kung asan activity ka
        CustomAdapter customAdapter = new CustomAdapter(HomeActivity.this, contactList);
        rvContacts.setAdapter(customAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayContacts();
    }

}