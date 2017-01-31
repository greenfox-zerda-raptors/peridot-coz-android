package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.junit.Assert.*;
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

    @Before
    public void setUp(){
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        welcomeTxt = (TextView) mainActivity.findViewById(R.id.welcomeText);
        preferences = mainActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
    }

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