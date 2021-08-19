package edu.neo.tpfinal

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.neo.tpfinal.view.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testButton(){
        Espresso.onView(ViewMatchers.withId(R.id.main_user)).perform(ViewActions.typeText("admin"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.main_pass)).perform(ViewActions.typeText("admin"),ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.main_b_ingresar)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.main_b_ingresar)).perform(ViewActions.click())

    }
    @Test
    fun usesAppContext(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.neo.tpfinal", appContext.packageName)
    }


}