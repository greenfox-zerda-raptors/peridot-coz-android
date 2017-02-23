package com.greenfox.peridot.peridot_coz_android.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mozgaanna on 16/02/17.
 */

public abstract class BaseFragment extends Fragment implements Callback {
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity().getApplicationContext());
        progressDialog.setMessage("loading...");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResponse(Call call, Response response) {
        progressDialog.dismiss();
        onData(call, response);
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        progressDialog.dismiss();
        onError(call, t);
    }

    public abstract void onData(Call call, Response response);

    public abstract void onError(Call call, Throwable t);

}
