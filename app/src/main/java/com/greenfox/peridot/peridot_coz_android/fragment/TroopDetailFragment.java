package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.peridot.peridot_coz_android.R.id.troopImage;

public class TroopDetailFragment extends Fragment {

    @Inject
    Services services;
    private Troop troop;

    public TroopDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.troop_detail, container, false);
        DaggerServiceComponent.builder().build().inject(this);

        Bundle bundle = getArguments();
        Troop troopFromPrevFrag = (Troop) bundle.getSerializable("troop");


        services.apiService.getTroopDetail(troopFromPrevFrag.getId()).enqueue(new Callback<Troop>() {
            @Override
            public void onResponse(Call<Troop> call, Response<Troop> response) {
                troop = response.body();
            }

            @Override
            public void onFailure(Call<Troop> call, Throwable t) {

            }
        });

        ImageView troopImage = (ImageView) contentView.findViewById(R.id.detailTroopImage);
        TextView troopHP = (TextView) contentView.findViewById(R.id.troopHP);
        TextView troopDP = (TextView) contentView.findViewById(R.id.troopDP);
        TextView troopAP = (TextView) contentView.findViewById(R.id.troopAP);
        troopImage.setImageResource(R.drawable.troop);
        troopHP.setText(String.valueOf("HP: " + troop.getHp()));
        troopDP.setText(String.valueOf("DP: " + troop.getDefensePower()));
        troopAP.setText(String.valueOf("AP: " + troop.getAttackPower()));

        return contentView;
    }
}
