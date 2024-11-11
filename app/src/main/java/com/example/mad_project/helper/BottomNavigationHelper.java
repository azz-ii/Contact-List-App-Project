package com.example.mad_project.helper;
import android.app.Activity;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.mad_project.HomeActivity;
import com.example.mad_project.AddActivity;
import com.example.mad_project.SearchActivity;
import com.example.mad_project.R;

public class BottomNavigationHelper {
    public static void setupBottomNavigation(final Activity activity, BottomNavigationView bottomNavigationView) {
        // Set the current selected item based on the activity
        if (activity instanceof HomeActivity) {
            bottomNavigationView.setSelectedItemId(R.id.btn_home);
        } else if (activity instanceof AddActivity) {
            bottomNavigationView.setSelectedItemId(R.id.btn_add);
        } else if (activity instanceof SearchActivity) {
            bottomNavigationView.setSelectedItemId(R.id.btn_search);
        }

        // Set up the item selection listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Avoid reloading the same activity
            if (itemId == R.id.btn_home && !(activity instanceof HomeActivity)) {
                activity.startActivity(new Intent(activity, HomeActivity.class));
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                activity.finish();
                return true;
            } else if (itemId == R.id.btn_add && !(activity instanceof AddActivity)) {
                activity.startActivity(new Intent(activity, AddActivity.class));
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                activity.finish();
                return true;
            } else if (itemId == R.id.btn_search && !(activity instanceof SearchActivity)) {
                activity.startActivity(new Intent(activity, SearchActivity.class));
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                activity.finish();
                return true;
            }

            return false;
        });
    }
}
