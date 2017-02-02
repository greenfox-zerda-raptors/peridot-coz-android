package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mozgaanna on 25/01/17.
 */

public class BuildingsOverviewFragment extends Fragment {

    public ArrayList<Building> buildings = new ArrayList<>();
    public ListView listView;
    public ArrayAdapter<Building> adapter;
    @Inject
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);

        listView = (ListView)contentView.findViewById(R.id.listViewBuilding);

        apiService.getBuildings(1).enqueue(new Callback<ArrayList<Building>>() {
            @Override
            public void onResponse(Call<ArrayList<Building>> call, Response<ArrayList<Building>> response) {
                buildings = response.body();
            }
            @Override
            public void onFailure(Call<ArrayList<Building>> call, Throwable t) {
            }});

        adapter = new ArrayAdapter<Building>(getActivity().getBaseContext(), 0,buildings);

        listView.setAdapter(adapter);

        return contentView;
    }


}
