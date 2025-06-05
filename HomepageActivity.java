package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomepageActivity extends AppCompatActivity {

    private HashMap<Integer, Fragment> fragmentMap ;
    private FragmentManager fragmentManager;

    private FrameLayout frameLayout;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        //to hide actionbar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_Navigation);
        frameLayout = findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nav_home,new HomeFragment());
        fragmentMap.put(R.id.nav_yoga,new YogaFragment());
        fragmentMap.put(R.id.nav_profile,new ProfileFragment());
        fragmentMap.put(R.id.nav_timer,new TimerFragment());

        replaceFragment(fragmentMap.get(R.id.nav_home));

       navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = fragmentMap.get(item.getItemId());
                   if(selectedFragment != null){
                       replaceFragment(selectedFragment);
                       return true;
               }
               return false;
           }

       });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.body,fragment);
        transaction.commit();
    }
}