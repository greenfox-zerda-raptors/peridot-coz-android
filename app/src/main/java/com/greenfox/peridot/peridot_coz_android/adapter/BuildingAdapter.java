package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        ImageView buildingImage = (ImageView) convertView.findViewById(R.id.buildingImage);
        TextView buildingType = (TextView) convertView.findViewById(R.id.buildingName);
        TextView buildingLevel = (TextView) convertView.findViewById(R.id.buildingLevel);
        if (building.getType().equals("Townhall")) {buildingImage.setImageResource(R.drawable.town);}
        if (building.getType().equals("Farm")) {buildingImage.setImageResource(R.drawable.farm);}
        if (building.getType().equals("Mine")) {buildingImage.setImageResource(R.drawable.mine);}
        if (building.getType().equals("Barrack")) {buildingImage.setImageResource(R.drawable.barrack);}
        buildingType.setText(String.valueOf(building.getType() + " " + (position+1)));
        buildingLevel.setText(String.valueOf("Level " + building.getLevel()));

        return convertView;
    }


}
