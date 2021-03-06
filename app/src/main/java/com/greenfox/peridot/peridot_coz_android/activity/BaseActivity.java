package com.greenfox.peridot.peridot_coz_android.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mozgaanna on 16/02/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements Callback {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
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
