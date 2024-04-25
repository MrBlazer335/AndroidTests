package com.example.xmlplease

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class StatisticsScreenTest {
    private val sharedPreferencesKey = "com.example.xmlplease"
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    @get:Rule
    val activityRule = ActivityScenarioRule(StatisticsScreen::class.java)

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    }

    @Test
    fun checkStatistics() {
        Espresso.onView(ViewMatchers.withTagValue(equalTo("totalReps")))
            .check(ViewAssertions.matches(ViewMatchers.withText("Всего выполнено повторений: 10")))
    }
}