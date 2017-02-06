package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;


/**
 * Created by mozgaanna on 25/01/17.
 */

public class KingdomOverviewFragment extends Fragment{
    Button buildingButton, troopButton, resourceButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.kingdom_overview_layout, container, false);
        buildingButton = (Button) contentView.findViewById(R.id.buildings_button);
        troopButton = (Button) contentView.findViewById(R.id.troops_button);
        resourceButton = (Button) contentView.findViewById(R.id.resources_button);


        buildingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new BuildingsOverviewFragment());
            }
        });
        troopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new TroopsOverviewFragment());
            }
        });
        resourceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new ResourcesOverviewFragment());
            }
        });

        return contentView;
    }
}
