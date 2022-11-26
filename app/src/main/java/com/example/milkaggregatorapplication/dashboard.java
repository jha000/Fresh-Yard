package com.example.milkaggregatorapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    homeFragment HomeFragment = new homeFragment();
    productFragment ProductFragment = new productFragment();
    subscribeFragment SubscribeFragement = new subscribeFragment();
    profileFragment ProfileFragment = new profileFragment();
    orderFragment OrderFragement = new orderFragment();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);





        bottom_navigation=findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,HomeFragment).commit();
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,HomeFragment).commit();


//                        Toast.makeText(dashboard.this, "Home", Toast.LENGTH_SHORT).show();

                        return true;


//                    case R.id.product:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ProductFragment).commit();
////                        Toast.makeText(dashboard.this, "Product", Toast.LENGTH_SHORT).show();
//                        return true;

                    case R.id.subscribe:
                 getSupportFragmentManager().beginTransaction().replace(R.id.container,SubscribeFragement).commit();
//                        Toast.makeText(dashboard.this, "Subscription", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.profile:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,ProfileFragment).commit();
//                        Toast.makeText(dashboard.this, "Profile", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.myorder:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,OrderFragement).commit();
//                        Toast.makeText(dashboard.this, "My Order", Toast.LENGTH_SHORT).show();
                        return true;
                }



                return false;
            }
        });
    }




    }
