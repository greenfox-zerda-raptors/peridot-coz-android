package com.greenfox.peridot.peridot_coz_android.provider;

import android.util.Log;

import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;

import javax.inject.Inject;

/**
 * Created by bedij on 2017. 02. 14..
 */

public class Services {
    @Inject
    public ApiService apiService;
    @Inject
    public ApiLoginService apiLoginService;

    Services(){
        DaggerApiComponent.builder().build().inject(this);
    }

    public void setApiService() {
        this.apiService = new ApiProvider().provideRestApiManager();
    }
}
