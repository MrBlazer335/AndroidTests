package com.example.xmlplease

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val sharedPreferencesKey = "com.example.xmlplease"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE)

    }

    @Test
    fun createNewUserWithNoData() {
        sharedPreferences.edit().clear().apply()
        onView(withId(R.id.saveButton)).perform(click())
        val currentName = sharedPreferences.getString("user_name", "")
        assertEquals("", currentName)

    }
    @Test
    fun createUserWthCoolName(){
        sharedPreferences.edit().clear().apply()
        val coolName = "Крутой пользователь"
        val weight = "85"
        onView(withId(R.id.name)).perform(ViewActions.replaceText(coolName))
        onView(withId(R.id.weight)).perform(ViewActions.replaceText(weight))
        onView(withId(R.id.saveButton)).perform(click())

        val sharedPreferences2 = context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE)
        val currentNameValue = sharedPreferences2.getString("user_name", "")
        val currentWeightrValue = sharedPreferences2.getFloat("user_weight", 0f)

        assertEquals("Крутой пользователь", currentNameValue)
        assertEquals(85.0f,currentWeightrValue)

    }
    @Test
    fun createUserFemale(){
        val coolName = "@ЖенскоеИмя"
        val weight = "55"
        onView(withId(R.id.name)).perform(ViewActions.replaceText(coolName))
        onView(withId(R.id.weight)).perform(ViewActions.replaceText(weight))
        onView(withId(R.id.maleOrFemale)).perform(ViewActions.click())
        onView(withId(R.id.saveButton)).perform(click())


        val sharedPreferences3 = context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE)
        val currentNameValue = sharedPreferences3.getString("user_name", "")
        val currentWeightrValue = sharedPreferences3.getFloat("user_weight", 0f)
        val currentGender = sharedPreferences3.getString("gender","")

        assertEquals("@ЖенскоеИмя", currentNameValue)
        assertEquals(55.0f,currentWeightrValue)
        assertEquals("female",currentGender)
        sharedPreferences.edit().clear().apply()

    }

    }

