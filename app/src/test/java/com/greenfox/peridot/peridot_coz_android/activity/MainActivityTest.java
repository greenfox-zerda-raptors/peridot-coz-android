package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.fragment.BattleOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.KingdomOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.ResourcesOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.UserOverviewFragment;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    private MainActivity mainActivity;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private NavigationView navigationOptions;

    @Before
    public void setUp(){
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
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
    public void testIfEmptySharedPrefMainGoesToLogin() throws Exception {
        Assert.assertEquals(LoginActivity.class.getName(), shadowOf(mainActivity).getNextStartedActivity().getComponent().getClassName());

    }

//    @Test
//    public void testIfUserWelcomed(){
//        Assert.assertEquals("Welcome aaa!", ShadowToast.getTextOfLatestToast());
//    }

    @Test
    public void testIfLogoutClickedSharedPrefGoesEmpty(){
        MenuItem item = new RoboMenuItem(R.id.logout);
        mainActivity.onOptionsItemSelected(item);
        Assert.assertEquals("", preferences.getString("username",""));
        Assert.assertEquals("", preferences.getString("password",""));
    }

    @Test
    public void testNavDrawerAppearing() throws Exception {
        Assert.assertNotNull(mainActivity.getDrawerToggleDelegate());
    }

    @Test
    public void testNavigationOptionItems() throws Exception {
        navigationOptions = (NavigationView) mainActivity.findViewById(R.id.nav_view);
        Assert.assertEquals("Your Kingdom", navigationOptions.getMenu().getItem(0).toString());
        Assert.assertEquals("Buildings", navigationOptions.getMenu().getItem(1).toString());
        Assert.assertEquals("Troops", navigationOptions.getMenu().getItem(2).toString());
        Assert.assertEquals("Battle", navigationOptions.getMenu().getItem(3).toString());
        Assert.assertEquals("Resources", navigationOptions.getMenu().getItem(4).toString());
        Assert.assertEquals("Leaderboard", navigationOptions.getMenu().getItem(5).toString());
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
    public void clickNavMenuItem5ShouldRedirectToResourcesOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(4));
        Assert.assertEquals(ResourcesOverviewFragment.class, mainActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame).getClass());
    }

    @Test
    public void clickNavMenuItem6ShouldRedirectToUserOverviewFragment() {
        mainActivity.onNavigationItemSelected(navigationOptions.getMenu().getItem(5));
        Assert.assertEquals(UserOverviewFragment.class, mainActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame).getClass());
    }

}
