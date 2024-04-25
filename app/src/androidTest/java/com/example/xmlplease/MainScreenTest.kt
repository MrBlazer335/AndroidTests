package com.example.xmlplease

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    private val sharedPreferencesKey = "com.example.xmlplease"
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    @get:Rule
    val activityRule = ActivityScenarioRule(MainScreen::class.java)
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    }
    @Test
    fun checkIfTextIsRight() {
        val userName = sharedPreferences.getString("user_name", "")
        val expectedText = "Здравствуйте, $userName"
        Espresso.onIdle()
        onView(withId(R.id.userNameView)).check(matches(withText(expectedText)))
    }
    @Test
    fun checkOpenMenuAndPressButton(){
        onView(withId(R.id.my_drawer_layout)).perform(open())
        onView(withId(R.id.nav_settings)).perform(click())
        onView(withId(R.id.name)).check(matches(isDisplayed()))
    }
    @Test
    fun checkOpenPhysicMenue(){
        onView(withId(R.id.my_drawer_layout)).perform(open())
        onView(withId(R.id.nav_physics)).perform(click())
        onView(withId(R.id.spinnerExercise)).check(matches(isDisplayed()))
    }

}
