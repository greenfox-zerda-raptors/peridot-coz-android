package com.greenfox.peridot.peridot_coz_android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.ResourceAdapter;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;

import java.util.ArrayList;

/**
 * Created by mozgaanna on 01/02/17.
 */

public class ResourcesOverviewFragment extends Fragment {
    ResourceAdapter resourceAdapter;
    ArrayList<Resource> resourceList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.resources_overview_layout, container, false);
        return contentView;

        //TODO: "resourceAdapter = new ResourceAdapter(somethinghereidk, resourceList);"
    }


}
