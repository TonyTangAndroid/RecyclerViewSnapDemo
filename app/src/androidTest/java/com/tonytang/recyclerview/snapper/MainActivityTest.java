package com.tonytang.recyclerview.snapper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.tonytang.recyclerview.snapper.TestUtils.withRecyclerView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityScenarioRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void itemClick_first() {
    onView(ViewMatchers.withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    //Have to trigger scrolled down
    onView(ViewMatchers.withId(R.id.recycler_view)).perform(ViewActions.swipeDown());

    onView(withId(R.id.tv_selected_item)).check(matches(withText("Selected Tier 0")));
  }


  @Test
  public void itemClick_last() {
    onView(ViewMatchers.withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));

    //Have to trigger scrolled up
    onView(ViewMatchers.withId(R.id.recycler_view)).perform(ViewActions.swipeUp());

    onView(withId(R.id.tv_selected_item)).check(matches(withText("Selected Tier 9")));

  }

  @Test
  public void selectedItem_shouldBeUpdated() {
    onView(ViewMatchers.withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));

    //Have to trigger scrolled up
    onView(ViewMatchers.withId(R.id.recycler_view)).perform(ViewActions.swipeUp());

    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(9, R.id.text))
        .check(matches(withText("Selected Tier 9")));

  }

}
