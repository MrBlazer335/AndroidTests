package com.example.xmlplease

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhysicsScreenTest {
    private val sharedPreferencesKey = "com.example.xmlplease"
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    @get:Rule
    val activityRule = ActivityScenarioRule(PhysicsScreen::class.java)

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    }

    @Test
    fun changeExerciseAndAddIt() {
        onView(withId(R.id.spinnerExercise)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Отжимания")))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinnerExercise)).check(matches(withSpinnerText(containsString("Отжимания"))))

        onView(withId(R.id.editTextReps)).perform(ViewActions.replaceText("10"))
        onView(withId(R.id.buttonSave)).perform(click())
    }

}