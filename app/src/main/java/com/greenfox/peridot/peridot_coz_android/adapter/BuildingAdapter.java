package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import java.util.ArrayList;

public class BuildingAdapter extends ArrayAdapter<Building> {

    public BuildingAdapter(Context context, ArrayList<Building> buildings) {
        super(context, 0, buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Building building = super.getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buildings_overview_listitem, parent, false);
        }
        TextView buildingType = (TextView) convertView.findViewById(R.id.buildingName);
        TextView buildingLevel = (TextView) convertView.findViewById(R.id.buildingLevel);
        buildingType.setText(String.valueOf(building.getType() + " " + (position+1)));
        buildingLevel.setText(String.valueOf("Level " + building.getLevel()));

        return convertView;
    }

}
