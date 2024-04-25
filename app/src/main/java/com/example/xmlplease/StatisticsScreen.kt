package com.example.xmlplease

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class StatisticsActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var exerciseStatistics: ExerciseStatistics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistic_scene)
        sharedPreferences = getSharedPreferences("com.example.xmlplease", MODE_PRIVATE)
        exerciseStatistics = ExerciseStatistics(this)

        val layoutExerciseStats = findViewById<LinearLayout>(R.id.layoutExerciseStats)
        exerciseStatistics.displayStatistics(layoutExerciseStats, sharedPreferences)

        val buttonClearStats = findViewById<Button>(R.id.buttonClearStats)
        buttonClearStats.setOnClickListener {
            clearStatistics(sharedPreferences)
            startActivity(Intent(this,MainScreen::class.java))
            finish()
        }
    }

    private fun clearStatistics(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().remove("exercises").apply()
        sharedPreferences.edit().remove("totalExercises").apply()
        sharedPreferences.edit().remove("totalReps").apply()

    }

}