package com.greenfox.peridot.peridot_coz_android;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
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
    private Button loginButton;
    private EditText loginUsername;
    private EditText loginPassword;

    @Before
    public void setUp() throws Exception
    {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        loginButton = (Button) loginActivity.findViewById(R.id.loginButton);
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
    public void testLoginToastOnWrongUserNameOrPassword(){
        loginUsername.setText("wrongojgfdig");
        loginPassword.setText("wronjdfkva");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Wrong username/password", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testLoginButtonWithEmptyUsernameOrPasswordStaysOnLoginActivity(){
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Please fill in username/password", ShadowToast.getTextOfLatestToast());    }
}
