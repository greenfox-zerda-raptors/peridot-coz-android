package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;


/**
 * Created by mozgaanna on 01/02/17.
 */

public class ResourceAdapter extends ArrayAdapter<Resource> {

    public ResourceAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
       Resource resource = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resource, parent, false);
        }
        // Lookup view for data population
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView buildings = (TextView) convertView.findViewById(R.id.buildings);

        // Populate the data into the template view using the data object
        type.setText(resource.getType());
        amount.setText(resource.getAmount());
        //TODO: buildings.setText((CharSequence) resource.getBuildings()); getArrayListOfBuildings



        // Return the completed view to render on screen
        return convertView;
    }
}
