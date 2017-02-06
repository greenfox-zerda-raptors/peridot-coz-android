package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowToast;

import static org.robolectric.Shadows.shadowOf;

/**
 * Created by bedij on 2017. 01. 31..
 */
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
