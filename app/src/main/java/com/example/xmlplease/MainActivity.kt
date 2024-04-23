package com.example.xmlplease

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("com.example.xmlplease", MODE_PRIVATE)

    }

    override fun onResume() {
        super.onResume()

        if (sharedPreferences?.getBoolean("firstrun", true) == true) {
            setContentView(R.layout.activity_main)
            val button = findViewById<FloatingActionButton>(R.id.saveButton)
            button.setOnClickListener {
                val userName = findViewById<EditText>(R.id.name).text.toString()
                val weight = findViewById<EditText>(R.id.weight).text.toString()
                try {
                    if (userName.isNotEmpty() && weight.isNotEmpty()) {
                        sharedPreferences!!.edit().putString("user_name", userName)
                            .putString("user_weight", weight).apply()
                        sharedPreferences!!.edit().putBoolean("firstrun", false).apply()
                        startActivity(Intent(this, MainScreen::class.java))
                    } else {
                        // Запрос повторного ввода данных
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
}



