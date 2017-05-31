package com.example.admin.abc;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<Login> mainActivityActivityTestRule = new ActivityTestRule<Login>(Login.class);

    @Test
    public void changeText() {
/*
        //Check that original text is displayed
        onView(withId(R.id.top_text)).check(matches(withText("Hello World!")));*/

        //Send text to our edit text
       /* onView(withContentDescription("editText_user")).perform(typeText("User Name"));
onView((withContentDescription("editText_password"))).perform(typeText("Password"));
        //Click on change button
        onView(withText("sign_in_button")).perform(click());*/
       onView(withId(R.id.editText_user)).perform(replaceText("User Name"));
        onView(withId(R.id.editText_password)).perform(replaceText("Password"));
        onView(withId(R.id.sign_in_button)).perform(click());


        // R class ID identifier for entering username

      /*  //Check that new text is displayed
        onView(withId(R.id.top_text)).check(matches(withText("Whoa Espresso is easy!")));*/
    }
}