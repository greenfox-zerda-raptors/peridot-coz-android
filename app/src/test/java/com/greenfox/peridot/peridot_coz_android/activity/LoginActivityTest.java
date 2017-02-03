package com.greenfox.peridot.peridot_coz_android.activity;

import android.os.Build;
import android.widget.EditText;

import com.greenfox.peridot.peridot_coz_android.BuildConfig;
import com.greenfox.peridot.peridot_coz_android.R;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.robolectric.Shadows.shadowOf;


/**
 * Created by mozgaanna on 20/01/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private EditText loginUsername;
    private EditText loginPassword;

    @Before
    public void setUp() throws Exception
    {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        loginUsername = (EditText) loginActivity.findViewById(R.id.loginName);
        loginPassword = (EditText) loginActivity.findViewById(R.id.loginPassword);
    }

    @Test
    public void testLoginButtonWithRightUsernameAndPasswordGoesToMainActivity(){
        loginUsername.setText("aaa");
        loginPassword.setText("aaa");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals(MainActivity.class.getName(), shadowOf(loginActivity).getNextStartedActivity().getComponent().getClassName());
    }

    @Test
    public void testRegisterButtonGoesToRegisterActivity(){
        loginActivity.findViewById(R.id.registerHereButton).performClick();
        Assert.assertEquals(RegisterActivity.class.getName(), shadowOf(loginActivity).getNextStartedActivity().getComponent().getClassName());
    }

    @Test
    public void testLoginToastOnWrongUserName(){
        loginUsername.setText("wrongojgfdig");
        loginPassword.setText("wronjdfkva");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("No such user exists", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testLoginToastOnWrongPassword(){
        loginUsername.setText("aaa");
        loginPassword.setText("wronjdfkva");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Wrong password", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testLoginButtonWithEmptyUsernameOrPasswordStaysOnLoginActivity(){
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Please fill in username/password", ShadowToast.getTextOfLatestToast());    }
}
