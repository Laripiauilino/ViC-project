package com.larissa.integrativeproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.larissa.integrativeproject.presentation.screen.HomeActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)

class HomeActivityTest{
    /**
     * Instrumented test, which will to launch home screen with tablayout.
     *

     */

    @get:Rule
    val homeActivity = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun start_form() {
        onView(withId(R.id.tabLayout)).perform(click())

    }
}