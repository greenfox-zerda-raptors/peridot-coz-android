package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.greenfox.peridot.peridot_coz_android.R;

/**
 * Created by mozgaanna on 25/01/17.
 */

public class BuildingsOverviewFragment extends Fragment {

    FloatingActionButton mainFab, mineFab, farmFab;
    boolean isMainFabOpen;
    Animation mainFabRotateLeft, mainFabRotateRight, appearSmallFab, disappearSmallFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);
        mainFab = (FloatingActionButton) contentView.findViewById(R.id.mainFab);
        mineFab = (FloatingActionButton) contentView.findViewById(R.id.mineFab);
        farmFab = (FloatingActionButton) contentView.findViewById(R.id.farmFab);
        isMainFabOpen = false;
        mainFabRotateLeft = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_left);
        mainFabRotateRight = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_right);
        appearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.appear_small_fab);
        disappearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.disappear_small_fab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMainFabOpen) {
                    mainFab.startAnimation(mainFabRotateLeft);
                    mineFab.startAnimation(disappearSmallFab);
                    farmFab.startAnimation(disappearSmallFab);
                    mineFab.setClickable(false);
                    farmFab.setClickable(false);
                    isMainFabOpen = false;
                } else {
                    mainFab.startAnimation(mainFabRotateRight);
                    mineFab.startAnimation(appearSmallFab);
                    farmFab.startAnimation(appearSmallFab);
                    mineFab.setClickable(true);
                    farmFab.setClickable(true);
                    isMainFabOpen = true;
                }
            }
        });
        return contentView;
    }
}
