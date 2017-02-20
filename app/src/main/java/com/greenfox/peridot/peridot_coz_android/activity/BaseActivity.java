package com.greenfox.peridot.peridot_coz_android.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
    public void onFailure(final Call call, Throwable t) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setMessage("Do you want to retry?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: call onData or onResponse
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        progressDialog.dismiss();
        onError(call, t);
    }

    public abstract void onData(Call call, Response response);

    public abstract void onError(Call call, Throwable t);

}
