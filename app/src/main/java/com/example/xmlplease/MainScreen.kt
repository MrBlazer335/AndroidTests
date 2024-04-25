package com.example.xmlplease

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainScreen : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_scene)

        val userNameText = findViewById<TextView>(R.id.userNameView)
        val sharedPreferences: SharedPreferences? =
            getSharedPreferences("com.example.xmlplease", MODE_PRIVATE)
        userNameText.text = "Здравствуйте, " + sharedPreferences?.getString("user_name", "")
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view_main_screen)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_settings -> {
                    sharedPreferences!!.edit().putBoolean("firstrun", true).apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.nav_physics -> {
                    startActivity(Intent(this, PhysicsScreen::class.java))
                    true
                }

                R.id.statistics -> {
                    startActivity(Intent(this, StatisticsScreen::class.java))
                    true
                }

                R.id.nav_whater -> {
                    startActivity(Intent(this,WaterScreen::class.java))
                    true
                }

                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
