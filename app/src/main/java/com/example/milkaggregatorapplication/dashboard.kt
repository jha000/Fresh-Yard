package com.example.milkaggregatorapplication

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.milkaggregatorapplication.homeFragment
import com.example.milkaggregatorapplication.subscribeFragment
import com.example.milkaggregatorapplication.profileFragment
import com.example.milkaggregatorapplication.orderFragment
import android.os.Bundle
import com.example.milkaggregatorapplication.R

class dashboard : AppCompatActivity() {
    var bottom_navigation: BottomNavigationView? = null
    var HomeFragment = homeFragment()
    var SubscribeFragement = subscribeFragment()
    var ProfileFragment = profileFragment()
    var OrderFragement = orderFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bottom_navigation = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment).commit()
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment)
                        .commit()


//                        Toast.makeText(dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                    return@OnNavigationItemSelectedListener true
                }
                R.id.subscribe -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SubscribeFragement).commit()
                    //                        Toast.makeText(dashboard.this, "Subscription", Toast.LENGTH_SHORT).show();
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ProfileFragment).commit()
                    //                        Toast.makeText(dashboard.this, "Profile", Toast.LENGTH_SHORT).show();
                    return@OnNavigationItemSelectedListener true
                }
                R.id.myorder -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, OrderFragement).commit()
                    //                        Toast.makeText(dashboard.this, "My Order", Toast.LENGTH_SHORT).show();
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}