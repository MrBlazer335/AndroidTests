package com.example.xmlplease

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class PhysicsScreen : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var exerciseStatistics: ExerciseStatistics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.physic_scene)
        sharedPreferences = getSharedPreferences("com.example.xmlplease", MODE_PRIVATE)
        exerciseStatistics = ExerciseStatistics(this)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            addPhysics()
//                val layoutStats = findViewById<LinearLayout>(R.id.layoutExerciseStats)
//                 exerciseStatistics.displayStatistics(layoutStats, sharedPreferences)
        }
    }

    private fun addPhysics() {
        val spinnerExercise = findViewById<Spinner>(R.id.spinnerExercise)
        val selectedExercise = spinnerExercise.selectedItem?.toString() ?: ""
        val editTextReps = findViewById<EditText>(R.id.editTextReps)
        val reps = editTextReps.text.toString().toIntOrNull() ?: 0

        val totalExercises = sharedPreferences.getInt("totalExercises", 0)
        val totalReps = sharedPreferences.getInt("totalReps", 0)

        val newTotalExercises = totalExercises + 1
        val newTotalReps = totalReps + reps

        sharedPreferences.edit().apply {
            putInt("totalExercises", newTotalExercises)
            putInt("totalReps", newTotalReps)
            apply()
        }

        val exercisesJson = sharedPreferences.getString("exercises", "[]")
        try {
            val jsonArray = JSONArray(exercisesJson)
            val jsonObject = JSONObject()
            jsonObject.put("type", selectedExercise)
            jsonObject.put("reps", reps)
            jsonArray.put(jsonObject)

            sharedPreferences.edit().putString("exercises", jsonArray.toString()).apply()
        } catch (e: JSONException) {
            e.printStackTrace()
        }


    }
}
