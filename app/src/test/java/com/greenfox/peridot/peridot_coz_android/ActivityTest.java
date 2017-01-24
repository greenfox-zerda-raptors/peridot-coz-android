package com.greenfox.peridot.peridot_coz_android;

import android.os.Build;
import android.widget.TextView;
import com.greenfox.peridot.peridot_coz_android.activity.LoggedInActivity;
import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertThat;

/**
 * Created by mozgaanna on 20/01/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class ActivityTest {
    /// TODO implement test with new activities
}
