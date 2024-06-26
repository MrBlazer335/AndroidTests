package com.example.xmlplease

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("com.example.xmlplease", MODE_PRIVATE)

    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onResume() {
        super.onResume()

        if (sharedPreferences?.getBoolean("firstrun", true) == true) {
            setContentView(R.layout.activity_main)
            val button = findViewById<FloatingActionButton>(R.id.saveButton)
            button.setOnClickListener {
                val userName = findViewById<EditText>(R.id.name).text.toString()
                val weightText = findViewById<EditText>(R.id.weight).text.toString()
                val weight = weightText.toFloatOrNull() ?: 0f

                val switch = findViewById<Switch>(R.id.maleOrFemale)
                try {
                    if (userName.isNotEmpty() && weight > 0) {
                        registerUser(switch, sharedPreferences, userName, weight)
                        startActivity(Intent(this, MainScreen::class.java))
                    } else {
                        Toast.makeText(this@MainActivity, "Введите имя и вес", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Обработка ошибки при обработке данных
                    Toast.makeText(
                        this@MainActivity,
                        "Ошибка при сохранении данных",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        } else {
            startActivity(Intent(this, MainScreen::class.java))
        }
    }

    fun registerUser(
        @SuppressLint("UseSwitchCompatOrMaterialCode") switch: Switch,
        sharedPreferences: SharedPreferences?,
        userName: String,
        weight: Float
    ) {
        if (switch.isChecked) {
            sharedPreferences!!.edit().putString("user_name", userName)
                .putFloat("user_weight", weight).putString("gender", "female").apply()
            sharedPreferences.edit().putBoolean("firstrun", false).apply()
        } else {
            sharedPreferences!!.edit().putString("user_name", userName)
                .putFloat("user_weight", weight).putString("gender", "male").apply()
            sharedPreferences.edit().putBoolean("firstrun", false).apply()
        }

    }
}



