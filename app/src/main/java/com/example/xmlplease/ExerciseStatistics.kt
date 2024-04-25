package com.example.xmlplease

import android.content.Context
import android.content.SharedPreferences
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONException

class ExerciseStatistics(private val context: Context) {

    fun displayStatistics(layout: LinearLayout, sharedPreferences: SharedPreferences) {
        val totalExercises = sharedPreferences.getInt("totalExercises", 0)
        val totalReps = sharedPreferences.getInt("totalReps", 0)

        val textViewTotalExercises = TextView(context)
        textViewTotalExercises.text = "Всего выполнено упражнений: $totalExercises"
        textViewTotalExercises.textSize = 18f
        layout.addView(textViewTotalExercises)

        val textViewTotalReps = TextView(context)
        textViewTotalReps.text = "Всего выполнено повторений: $totalReps"
        textViewTotalReps.textSize = 18f
        layout.addView(textViewTotalReps)
        textViewTotalReps.tag = "totalReps"

        val exercisesJson = sharedPreferences.getString("exercises", "[]")
        try {
            val jsonArray = JSONArray(exercisesJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val exerciseType = jsonObject.getString("type")
                val exerciseReps = jsonObject.getInt("reps")

                val textViewExerciseStat = TextView(context)
                textViewExerciseStat.text = "$exerciseType: $exerciseReps повторений"
                textViewExerciseStat.textSize = 18f
                layout.addView(textViewExerciseStat)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
