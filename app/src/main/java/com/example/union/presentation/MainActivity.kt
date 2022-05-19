package com.example.union.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.union.R
import com.example.union.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView : BottomNavigationView = findViewById(R.id.nav_view)

        APP = this
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment
        navController = navHostFragment.navController

        navView.setupWithNavController(navController)
        //navController = Navigation.findNavController(this,R.id.nav_graph)


    }
}