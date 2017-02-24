package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.peridot.peridot_coz_android.R;
import retrofit2.Call;
import retrofit2.Response;

public class BattleOverviewFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battle_overview_layout, container, false);
    }

    @Override
    public void onData(Call call, Response response) {

    }

    @Override
    public void onError(Call call, Throwable t) {

    }
}
