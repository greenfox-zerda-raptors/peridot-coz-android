package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import java.util.ArrayList;


/**
 * Created by mozgaanna on 10/02/17.
 */

public class UserAdapter extends ArrayAdapter<User>{
    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = super.getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.users_overview_listitem, parent, false);
        }

        TextView username = (TextView) convertView.findViewById(R.id.userName);
        TextView points = (TextView) convertView.findViewById(R.id.points);
        
        username.setText(String.valueOf("username: " + user.getUsername()));
        points.setText(String.valueOf("points: " + user.getPoints() ));
        
        return convertView;
    }

}

