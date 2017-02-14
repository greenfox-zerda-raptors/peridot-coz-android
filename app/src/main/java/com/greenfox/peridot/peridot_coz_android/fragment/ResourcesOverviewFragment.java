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
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerApiComponent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourcesOverviewFragment extends Fragment {

    ResourceAdapter resourceAdapter;
    ArrayList<Resource> resourceList;
    ListView resourcesListView;
    @Inject
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerApiComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.resources_overview_layout, container, false);

        resourceList = new ArrayList<>();

        resourcesListView = (ListView) contentView.findViewById(R.id.resourcesListView);

        resourceAdapter = new ResourceAdapter(
                getContext(),
                resourceList);
        resourcesListView.setAdapter(resourceAdapter);

        apiService.getResource(1).enqueue(new Callback<ResourceResponse>() {
            @Override
            public void onResponse(Call<ResourceResponse> call, Response<ResourceResponse> response) {
                resourceAdapter.clear();
                resourceAdapter.addAll(response.body().getResources());
            }

            @Override
            public void onFailure(Call<ResourceResponse> call, Throwable t) {
            }
        });

        return contentView;
    }
}
