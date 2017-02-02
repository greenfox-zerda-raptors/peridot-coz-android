package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.util.ArrayList;

/**
 * Created by mozgaanna on 25/01/17.
 */

public class BuildingsOverviewFragment extends Fragment {

    public ArrayList<Building> myMessageArray = new ArrayList<>();
    public ListView listView;
    public ArrayAdapter<Building> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);
        return contentView;
    }


}
