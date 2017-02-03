package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.util.ArrayList;

/**
 * Created by bedij on 2017. 02. 03..
 */

public class BuildingAdapter extends ArrayAdapter<Building> {

    TextView buildingType;
    TextView buildingLevel;
    Building building;

    public BuildingAdapter(Context context, ArrayList<Building> buildings) {
        super(context, 0, buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        building = super.getItem(super.getCount() - position - 1);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buildingview, parent, false);
        }
        buildingType = (TextView) convertView.findViewById(R.id.buildingName);
        buildingLevel = (TextView) convertView.findViewById(R.id.buildingLevel);
        buildingType.setText(String.format(building.getType() + " " + building.getId()));
        buildingLevel.setText(String.valueOf(building.getLevel()));

        return convertView;
    }

}
