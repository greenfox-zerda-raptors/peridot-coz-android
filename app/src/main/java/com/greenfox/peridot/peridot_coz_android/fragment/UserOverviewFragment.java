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
import com.greenfox.peridot.peridot_coz_android.adapter.TroopAdapter;
import com.greenfox.peridot.peridot_coz_android.adapter.UserAdapter;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.UserResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mozgaanna on 08/02/17.
 */

public class UserOverviewFragment extends Fragment {
    ListView usersList;
    User user;
    private ArrayList<User> users = new ArrayList<>();
    private UserAdapter userAdapter;
    @Inject
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerMainActivityComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.users_overview_layout, container, false);

        usersList = (ListView) contentView.findViewById(R.id.usersList);

        userAdapter = new UserAdapter(container.getContext(), users);
        usersList.setAdapter(userAdapter);

        apiService.getUser(1).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userAdapter.clear();
                userAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return contentView;
    }
}
