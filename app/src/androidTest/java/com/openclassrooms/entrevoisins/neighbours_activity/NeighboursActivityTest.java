package com.openclassrooms.entrevoisins.neighbours_activity;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
     * When we click on an item from the list, the neighbour detail activity is open correctly
     */

    @Test
    public void mNeighboursList_openNeighbourProfil() {

        ViewInteraction recyclerView =
                onView(allOf(withId(R.id.list_neighbours), isDisplayingAtLeast(60)));

        recyclerView
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(hasComponent(mNeighbourTestRule.getActivity().getComponentName()));
    }

    /**
    * When we click on the neighbour, we check that the name of the neighbour is display on the neighbour detail.
    */

    @Test
    public void checkNameNeighbourOpenningTest() {

        ViewInteraction recyclerView =
                onView(allOf(withId(R.id.list_neighbours), isDisplayingAtLeast(60)));
        recyclerView
                .perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView =
                onView( allOf(withId(R.id.profil_name_2), withText("Caroline"), isDisplayed()));
        textView
                .check(matches(withText("Caroline")));
    }

    /**
     * The Favorite page only show favorite Neighbour.
     */

    @Test
    public void checkFavoriteTabsShowFavoriteNeighbourOnly() {

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours), isDisplayingAtLeast(60),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        // on click sur le bouton favoris pour mettre notre utilisateur comme favoris
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fav_button),
                        childAtPosition( childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        floatingActionButton.perform(click());

        // click sur le bouton back
        ViewInteraction appCompatImageButton = onView   (
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.toolbarlayout), withContentDescription("Caroline")),
                                                1)),
                                0)
                        );
        appCompatImageButton.perform(click());

        // on Recupère le tabView qui est lié au viewpager
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"), isDisplayingAtLeast(60)));
        tabView.perform(click());

        // on change d'onglet pour se mettre sur l'onglet favorites
        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container), isDisplayingAtLeast(60)));
        viewPager.perform(swipeLeft());

        // on verifie que la liste de l'onglet favoris contient desormais 1 element
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_neighbours), isDisplayingAtLeast(60),
                        withParent(withId(R.id.container))));
        recyclerView2.check(withItemCount(1));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
