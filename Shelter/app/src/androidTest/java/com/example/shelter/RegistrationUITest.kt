package com.example.shelter

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.shelter.presentation.LogInAppActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RegistrationUITest {

    @get:Rule
    var activityRule: ActivityTestRule<LogInAppActivity> = ActivityTestRule(
        LogInAppActivity::class.java, false, false)

    private val validEmail: String = "newtest@mail.ru"
    private val correctPassword: String = "password"

    @Before
    fun setup() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        activityRule.launchActivity(intent)
        Espresso.onView((ViewMatchers.withId(R.id.registration)))
            .perform(ViewActions.click())
    }

    // Start Activity

    @Test
    fun clickLoginButton(){
        Espresso.onView(ViewMatchers.withId(R.id.registration))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledEmailEditText(){
        Espresso.onView(ViewMatchers.withId(R.id.email))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledPasswordEditText(){
        Espresso.onView(ViewMatchers.withId(R.id.password))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledCityText(){
        Espresso.onView(ViewMatchers.withId(R.id.city))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledPhoneNumberEditText(){
        Espresso.onView(ViewMatchers.withId(R.id.phone))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledUserEditText(){
        Espresso.onView(ViewMatchers.withId(R.id.editTxtYouAre))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }





}