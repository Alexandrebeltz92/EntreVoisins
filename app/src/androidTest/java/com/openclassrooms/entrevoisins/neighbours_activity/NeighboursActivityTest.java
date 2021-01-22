package com.openclassrooms.entrevoisins.neighbours_activity;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourProfil;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class NeighboursActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);

    @Rule
    public IntentsTestRule<MyNeighbourProfil> mNeighbourTestRule =
            new IntentsTestRule<>(MyNeighbourProfil.class);

    /**
     * When we click an item, the neighbour detail is shown
     */

    @Test
    public void mNeighboursList_openNeighbourProfil() {
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check((ViewAssertion) isDisplayingAtLeast(60))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()))
                .check(matches(isDisplayed()));
    }



    //  @Test
    //  public void mNeighboursList_openNeighbourProfil() {
    // Given : We click on the first list element
    //    onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    //
    //     onView(ViewMatchers.withId(R.id.my_neighbour_profil)).check(matches());
    // }

}
