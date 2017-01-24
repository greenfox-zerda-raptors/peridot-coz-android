package com.greenfox.peridot.peridot_coz_android;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by mozgaanna on 20/01/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class ActivityTest {
    /// TODO implement test with new activities

    private LoginActivity loginActivity;
    private Button loginButton;
    private EditText loginUsername;
    private EditText loginPassword;


    @Before
    public void setUp() throws Exception
    {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginButton = (Button) loginActivity.findViewById(R.id.loginButton);
        loginUsername = (EditText) loginActivity.findViewById(R.id.loginName);
        loginPassword = (EditText) loginActivity.findViewById(R.id.loginPassword);
    }

    @Test
    public void testLoginButtonToGoesMainActivity(){
        loginActivity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = new Intent(loginActivity, MainActivity.class);
        assertThat(shadowOf(loginActivity).getNextStartedActivity().equals(expectedIntent)
                );
    }
    @Test
    public void testLoginButtonWithEmptyUsernameOrPasswordStaysOnLoginActivity(){
        loginActivity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = new Intent(loginActivity, LoginActivity.class);
        assertThat(shadowOf(loginActivity).getNextStartedActivity().equals(expectedIntent)
        );
    }

    @Test
    public void testLoginButtonWithWrongUsernameOrPasswordStaysOnLoginActivity(){
        loginUsername.setText("wrongname");
        loginPassword.setText("wrongpassword");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = null;
        assertThat(shadowOf(loginActivity).getNextStartedActivity().equals(expectedIntent)
        );
    }


    @Test
    public void testLoginButtonWithRightPasswordGoesToMainActivity(){
        loginUsername.setText("aaa");
        loginPassword.setText("aaa");
        loginActivity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = new Intent(loginActivity, MainActivity.class);
        assertThat(shadowOf(loginActivity).getNextStartedActivity().equals(expectedIntent)
        );
    }
}
