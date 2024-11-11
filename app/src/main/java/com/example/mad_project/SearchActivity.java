package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.btn_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.btn_home){
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                return true;
            }else if(item.getItemId() == R.id.btn_add) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.btn_search) {
                return true;
            } else{
                return false;
            }

        });

    }
}