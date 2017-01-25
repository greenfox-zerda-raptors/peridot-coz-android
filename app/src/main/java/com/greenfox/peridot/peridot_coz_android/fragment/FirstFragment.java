package com.greenfox.peridot.peridot_coz_android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.peridot.peridot_coz_android.R;

/**
 * Created by mozgaanna on 25/01/17.
 */

public class FirstFragment extends Fragment {

    View contentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.first_layout, container, false);
        return contentView;
    }
}
