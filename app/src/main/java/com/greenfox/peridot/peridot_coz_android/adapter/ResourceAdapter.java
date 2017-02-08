package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import java.util.ArrayList;

public class ResourceAdapter extends ArrayAdapter<Resource> {

    public ResourceAdapter(Context context, int resourcesListView, ArrayList<Resource> resourceList) {
        super(context, 0, new ArrayList<Resource>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Resource resource = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resources_overview_listitem, parent, false);
        }

        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView buildings = (TextView) convertView.findViewById(R.id.buildings);

        type.setText(resource.getType());
        amount.setText(resource.getAmount());
        buildings.setText((CharSequence) resource.getBuildings());

        return convertView;
    }
}
