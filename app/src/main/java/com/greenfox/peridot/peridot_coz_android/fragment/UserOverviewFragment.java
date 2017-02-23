package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.UserAdapter;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApiComponent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.response.UsersResponse;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        DaggerApiComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.users_overview_layout, container, false);

        usersList = (ListView) contentView.findViewById(R.id.usersList);

        userAdapter = new UserAdapter(container.getContext(), users);
        usersList.setAdapter(userAdapter);

        apiService.getUsers().enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                userAdapter.clear();
                userAdapter.addAll(response.body().getUsers());
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
        return contentView;
    }
}
