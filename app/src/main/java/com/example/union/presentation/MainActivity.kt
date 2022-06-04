package com.example.union.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.union.R
import com.example.union.app.App
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels{
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        (applicationContext as App).appComponent.inject(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment
        navController = navHostFragment.navController

        navView.setOnItemReselectedListener { item ->
            navController.navigate(item.itemId)
        }
        navView.setupWithNavController(navController)
    }
}