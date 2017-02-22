package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.MenuItem;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;

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
}
