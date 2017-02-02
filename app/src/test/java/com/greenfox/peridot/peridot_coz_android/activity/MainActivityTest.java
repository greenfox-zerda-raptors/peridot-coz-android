package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.fragment.BattleOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.KingdomOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by bedij on 2017. 01. 31..
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    private MainActivity mainActivity;
    private TextView welcomeTxt;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationOptions;
    private FrameLayout contentFrame;

    @Before
    public void setUp(){
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);
        contentFrame = (FrameLayout) mainActivity.findViewById(R.id.content_frame);
        preferences = mainActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        navigationOptions = (NavigationView) mainActivity.findViewById(R.id.nav_view);
        editor = preferences.edit();
        editor.putString("username", "aaa");
        editor.putString("password", "aaa");
        editor.apply();
    }

    @Test
    public void testToolbarAppearing() throws Exception {
        Assert.assertNotNull(mainActivity.getSupportActionBar());
    }

    @Test
    public void testNavDrawerAppearing() throws Exception {
        Assert.assertNotNull(mainActivity.getDrawerToggleDelegate());
    }

    @Test
    public void testNavigationOptionItems() throws Exception {
        navigationOptions = (NavigationView) mainActivity.findViewById(R.id.nav_view);
        Assert.assertEquals("Your Kingdom", navigationOptions.getMenu().getItem(0).toString());
        Assert.assertEquals("Second Layout", navigationOptions.getMenu().getItem(1).toString());
        Assert.assertEquals("Communicate", navigationOptions.getMenu().getItem(2).toString());
        Assert.assertEquals("Share", navigationOptions.getMenu().getItem(2).getSubMenu().getItem(0).toString());
        Assert.assertEquals("Send", navigationOptions.getMenu().getItem(2).getSubMenu().getItem(1).toString());
    }

    @Test
    public void clickNavMenuItem1ShouldRedirectToKingdomOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(0));
        Assert.assertEquals(KingdomOverviewFragment.class, mainActivity.getSupportFragmentManager()
                   .findFragmentById(R.id.content_frame).getClass());
        }

    @Test
    public void clickNavMenuItem2ShouldRedirectToBuildingsOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(1));
        Assert.assertEquals(BuildingsOverviewFragment.class, mainActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame).getClass());
    }

    @Test
    public void clickNavMenuItem3ShouldRedirectToTroopsOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(2));
        Assert.assertEquals(TroopsOverviewFragment.class, mainActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame).getClass());
    }

    @Test
    public void clickNavMenuItem4ShouldRedirectToBattleOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(3));
        Assert.assertEquals(BattleOverviewFragment.class, mainActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame).getClass());
    }

    @Test
    public void testIfEmptySharedPrefMainGoesToLogin() throws Exception {
        Assert.assertEquals(LoginActivity.class.getName(), shadowOf(mainActivity).getNextStartedActivity().getComponent().getClassName());
    }
}
