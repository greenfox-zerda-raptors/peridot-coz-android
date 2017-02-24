package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.ResourceAdapter;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class ResourcesOverviewFragment extends BaseFragment<ResourceResponse> {

    ResourceAdapter resourceAdapter;
    ListView resourcesListView;
    @Inject
    Services services;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerServiceComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.resources_overview_layout, container, false);

        resourcesListView = (ListView) contentView.findViewById(R.id.resourcesListView);

        resourceAdapter = new ResourceAdapter(
                getContext(),
                new ArrayList<Resource>());
        resourcesListView.setAdapter(resourceAdapter);

        services.apiService.getResource().enqueue(this);
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {
        ResourceResponse resourceResponse = (ResourceResponse) response.body();
        resourceAdapter.clear();
        resourceAdapter.addAll(resourceResponse.getResources());
    }

    @Override
    public void onError(Call call, Throwable t) {

    }
}
