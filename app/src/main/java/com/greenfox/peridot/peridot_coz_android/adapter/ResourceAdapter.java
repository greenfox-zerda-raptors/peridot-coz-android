package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import java.util.ArrayList;

public class ResourceAdapter extends ArrayAdapter<Resource> {

    public ResourceAdapter(Context context, ArrayList<Resource> resourceList) {
        super(context, 0, resourceList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Resource resource = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resources_overview_listitem, parent, false);
        }
        ImageView resourceImage = (ImageView) convertView.findViewById(R.id.resourceImage);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView buildings = (TextView) convertView.findViewById(R.id.buildings);
        if (resource.getType().equals("gold")) {resourceImage.setImageResource(R.drawable.gold);}
        if (resource.getType().equals("food")) {resourceImage.setImageResource(R.drawable.food);}
        type.setText(resource.getType());
        amount.setText(String.valueOf(resource.getAmount()));
        buildings.setText(listTheBuildings());

        return convertView;
    }

    public StringBuilder listTheBuildings(){
        ArrayList<Building> buildings = new ArrayList<>();
        StringBuilder buildingString = new StringBuilder();

        for(Building building : buildings){
            buildingString.append(building).append(",");
        }
        return buildingString;
    }
}
