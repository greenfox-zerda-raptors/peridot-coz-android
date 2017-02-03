package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;

import java.util.ArrayList;

public class TroopAdapter extends ArrayAdapter<Troop>{

    public TroopAdapter(Context context, ArrayList<Troop> troops) {
        super(context, 0, troops);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Troop troop = super.getItem(super.getCount() - position - 1);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.troops_overview_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvHP = (TextView) convertView.findViewById(R.id.tvHP);
        TextView tvAP = (TextView) convertView.findViewById(R.id.tvAP);
        TextView tvDP = (TextView) convertView.findViewById(R.id.tvDP);
        // Populate the data into the template view using the data object
        tvHP.setText(String.format("AP: " + troop.getHp()));
        tvAP.setText(String.valueOf("AP: " + troop.getAttackPower()));
        tvDP.setText(String.valueOf("DP: " + troop.getDefensePower()));
        // Return the completed view to render on screen
        return convertView;
    }

}
