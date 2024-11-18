package com.example.mad_project;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.entity.Contact;
import com.example.mad_project.helper.BottomNavigationHelper;
import com.example.mad_project.helper.CustomAdapter;
import com.example.mad_project.helper.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ArrayList<Contact> contactList = new ArrayList<>();
    RecyclerView rvContacts;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EdgeToEdge.enable(this, SystemBarStyle.dark(android.graphics.Color.TRANSPARENT));

        displayContacts();

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContacts(newText);
                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayContacts();
    }

    private void displayContacts() {

        contactList.clear();
        DatabaseHelper dh = new DatabaseHelper(SearchActivity.this);
        Cursor cur = dh.readAllContacts();

        if (cur.getCount() != 0) {
            while (cur.moveToNext()) {
                Contact c = new Contact(
                        cur.getInt(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3)
                );
                contactList.add(c);

            }
        }
        rvContacts = findViewById(R.id.rvContacts);
        //Current Context
        CustomAdapter customAdapter = new CustomAdapter(SearchActivity.this, contactList);
        rvContacts.setAdapter(customAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
    }

    private void searchContacts(String s){

        contactList.clear();
        DatabaseHelper dh = new DatabaseHelper(SearchActivity.this);
        Cursor cur = dh.readContactsBySearch(s);

        if (cur.getCount() != 0) {
            while (cur.moveToNext()) {
                Contact c = new Contact(
                        cur.getInt(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3)
                );
                contactList.add(c);

            }
        }

        rvContacts = findViewById(R.id.rvContacts);
        //Current Context
        CustomAdapter customAdapter = new CustomAdapter(SearchActivity.this, contactList);
        rvContacts.setAdapter(customAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
    }


}