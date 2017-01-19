package com.greenfox.peridot.peridot_coz_android.activity;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

/**
 * Created by mozgaanna on 19/01/17.
 */
public class AsteriskPasswordTransformationMethod implements TransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {

    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source; // Store char sequence
        }
        public char charAt(int index) {
            return '*'; // This is the important part
        }
        public int length() {
            return mSource.length(); // Return default
        }
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
}

//took it from here: http://stackoverflow.com/questions/14051962/change-edittext-password-mask-character-to-asterisk
//should be working
