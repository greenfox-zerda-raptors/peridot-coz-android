package com.greenfox.peridot.peridot_coz_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.peridot.peridot_coz_android.R;

/**
 * Created by mozgaanna on 25/01/17.
 */

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.second_layout, container, false);
        return contentView;
    }
}
