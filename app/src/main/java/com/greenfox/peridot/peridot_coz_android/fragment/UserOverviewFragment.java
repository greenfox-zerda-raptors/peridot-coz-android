package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.UserAdapter;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.response.UsersResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class UserOverviewFragment extends BaseFragment {

    ListView usersList;
    private ArrayList<User> users = new ArrayList<>();
    private UserAdapter userAdapter;
    @Inject
    Services services;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerServiceComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.users_overview_layout, container, false);

        usersList = (ListView) contentView.findViewById(R.id.usersList);

        userAdapter = new UserAdapter(container.getContext(), users);
        usersList.setAdapter(userAdapter);

        services.apiService.getUsers().enqueue(this);
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {
        UsersResponse usersResponse = (UsersResponse) response.body();
        userAdapter.clear();
        userAdapter.addAll(usersResponse.getUsers());
    }

    @Override
    public void onError(Call call, Throwable t) {

    }
}
