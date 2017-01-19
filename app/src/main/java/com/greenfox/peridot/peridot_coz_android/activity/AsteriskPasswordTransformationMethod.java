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
            mSource = source;
        }
        public char charAt(int index) {
            return '*';
        }
        public int length() {
            return mSource.length();
        }
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }
    }
}
