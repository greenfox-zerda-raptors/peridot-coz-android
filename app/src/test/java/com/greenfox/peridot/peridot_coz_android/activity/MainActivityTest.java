package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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

    @Before
    public void setUp(){
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        welcomeTxt = (TextView) mainActivity.findViewById(R.id.welcomeText);
        toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);
        preferences = mainActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
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

/*    @Test
    public void clickMenuItem_shouldDelegateClickToFragment() {
        final MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        shadowOf(activity).clickMenuItem(R.id.item4);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Clicked Item 4");
    }*/

    @Test
    public void testIfEmptySharedPrefMainGoesToLogin() throws Exception {
        Assert.assertEquals(LoginActivity.class.getName(), shadowOf(mainActivity).getNextStartedActivity().getComponent().getClassName());

    }

    @Test
    public void testIfUserWelcomed(){
        Assert.assertEquals("Welcome aaa!", welcomeTxt.getText().toString());
    }

    @Test
    public void testIfLogoutClickedSharedPrefGoesEmpty(){
        mainActivity.findViewById(R.id.logoutButton).performClick();
        Assert.assertEquals("", preferences.getString("username",""));
        Assert.assertEquals("", preferences.getString("password",""));
    }

}