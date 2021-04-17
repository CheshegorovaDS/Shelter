package com.example.shelter

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginUITest {

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
        Espresso.onView((withId(R.id.logIn)))
            .perform(ViewActions.click())
    }

    // Start Activity
    @Test
    fun clickLoginButton(){
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledEmailEditText(){
        Espresso.onView(withId(R.id.email))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun enabledPasswordEditText(){
        Espresso.onView(withId(R.id.password))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    // Click login button

    // With entered email or password
    @Test
    fun clickButtonWithoutPassword() {
        Espresso.onView(withId(R.id.email)).perform(ViewActions.typeText(validEmail))
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun clickButtonWithoutEmail() {
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText(correctPassword))
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun clickButtonWithWrongPassword(){
        Espresso.onView(withId(R.id.email)).perform(ViewActions.typeText(validEmail))
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText("pa"))
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun clickButtonWithWrongEmail(){
        Espresso.onView(withId(R.id.email)).perform(ViewActions.typeText("newtestissart.com"))
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText(correctPassword))
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    //закрываем активити, а так как начинаем дело не с той активити, то выходим из приложения
    @Test
    fun clickButtonWithEmailAndPassword(){
        Espresso.onView(withId(R.id.email)).perform(ViewActions.typeText(validEmail))
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText(correctPassword))
        Espresso.onView(withId(R.id.logIn))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        Espresso.onView(withId(R.id.logIn)).perform(ViewActions.click())
//        Espresso.onView(withId(R.id.layoutLogin))
//            .check(ViewAssertions.matches(CoreMatchers.not(isDisplayed())))
        Espresso.onView(withId(R.id.email))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isEnabled())))
    }

    @Test
    fun checkNavigationLoginButton(){
        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.typeText(validEmail), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText(correctPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.logIn)).perform(ViewActions.click())
        //onView(withId(R.id.layoutLoading)).check(matches(isDisplayed()))
    }



    // Click back button
}