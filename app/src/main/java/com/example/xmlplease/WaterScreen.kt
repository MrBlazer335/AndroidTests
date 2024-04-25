package com.example.xmlplease

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WaterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.water_scene)

        val targetWater = findViewById<TextView>(R.id.textViewTargetWater)
        val currentWater = findViewById<TextView>(R.id.textViewCurrentWater)

        val sharedPreferences = getSharedPreferences("com.example.xmlplease", Context.MODE_PRIVATE)
        val weight = sharedPreferences.getFloat("user_weight", 0f)
        Log.d("water",sharedPreferences.getFloat("currentWater",0f).toString())


        val targetWaterValue = weight * 0.03 //

        targetWater.text = "Норма воды (л): $targetWaterValue"

        val currentWaterValue = sharedPreferences.getFloat("currentWater", 0f)
        currentWater.text = "Выпито сегодня (л): $currentWaterValue"

        val buttonSave = findViewById<Button>(R.id.buttonSaveWater)
        buttonSave.setOnClickListener {
            saveWater()
        }
        val resetButton = findViewById<Button>(R.id.buttonResetWater)
        resetButton.setOnClickListener{
            resetWater()
        }
    }

    fun saveWater() {
        val editTextWaterInput = findViewById<EditText>(R.id.editTextWaterInput)
        val waterInputText = editTextWaterInput.text.toString()
        if (waterInputText.isNotEmpty()) {
            val waterInput = waterInputText.toFloat()
            val sharedPreferences = getSharedPreferences("com.example.xmlplease", Context.MODE_PRIVATE)
            val currentWaterValue = sharedPreferences.getFloat("currentWater", 0f)
            val newCurrentWaterValue = currentWaterValue + waterInput
            sharedPreferences.edit().putFloat("currentWater", newCurrentWaterValue).apply()

            val currentWater = findViewById<TextView>(R.id.textViewCurrentWater)
            currentWater.text = "Выпито сегодня (л): $newCurrentWaterValue"
            editTextWaterInput.text.clear()
        } else {
            Toast.makeText(this, "Введите количество воды", Toast.LENGTH_SHORT).show()
        }
    }
    fun resetWater(){
        val sharedPreferences = getSharedPreferences("com.example.xmlplease",Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("currentWater").apply()
        val currentWater = findViewById<TextView>(R.id.textViewCurrentWater)
        currentWater.text = "Выпито сегодня (л): 0"


    }
}