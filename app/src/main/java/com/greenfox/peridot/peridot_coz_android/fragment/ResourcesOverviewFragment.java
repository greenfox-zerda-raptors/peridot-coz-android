package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
    ListView resourcesListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.resources_overview_layout, container, false);

        resourceList = new ArrayList<>();
        resourcesListView = (ListView) contentView.findViewById(R.id.resourcesListView);

        resourceAdapter = new ResourceAdapter(getActivity());
        resourcesListView.setAdapter(resourceAdapter);

        return contentView;
    }
}
