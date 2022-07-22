package com.example.animequotes

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.animequotes.util.Constant.DB_NAME
import com.example.animequotes.util.DatabaseUtil

private const val TAG = "MainActivity"
class MainActivity: AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        loggingDatabaseSize()

        setupActionBarWithNavController(navController)
    }

    private fun loggingDatabaseSize() {
        try {
            val dbSize = DatabaseUtil.getRoomDatabaseSize(this, DB_NAME)
            Log.d(TAG, "loggingDatabaseSize: $dbSize MB")
        }catch (e: Exception){
            Log.d(TAG, "loggingDatabaseSize: $e")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}