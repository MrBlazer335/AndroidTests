package com.example.xmlplease

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
class ExampleInstrumentedTest {

    private val sharedPreferencesKey = "com.example.xmlplease"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE).edit().clear().apply()
    }

    @Test
    fun createNewUserWithNoData() {
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE)
        onView(withId(R.id.saveButton)).perform(click())
        val currentName = sharedPreferences.getString("user_name", "")
        assertEquals("", currentName)
    }
    @Test
    fun createUserWthCoolName(){
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
    }
    fun createDummy(){
        val coolName = "Крутой пользователь"
        val weight = "85"
        onView(withId(R.id.name)).perform(ViewActions.replaceText(coolName))
        onView(withId(R.id.weight)).perform(ViewActions.replaceText(weight))
        onView(withId(R.id.saveButton)).perform(click())

        val sharedPreferences = context.getSharedPreferences(sharedPreferencesKey, MODE_PRIVATE)
        val currentNameValue = sharedPreferences.getString("user_name", "")
        val currentWeightrValue = sharedPreferences.getFloat("user_weight", 0f)

        assertEquals("Крутой пользователь", currentNameValue)
        assertEquals(85.0f,currentWeightrValue)
    }
    }

