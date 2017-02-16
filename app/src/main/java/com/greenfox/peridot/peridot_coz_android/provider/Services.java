package com.greenfox.peridot.peridot_coz_android.provider;

import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;

import javax.inject.Inject;

public class Services {

    public ApiService apiService;
    public ApiLoginService apiLoginService;

    public Services(ApiService apiService, ApiLoginService apiLoginService){
        this.apiService = apiService;
        this.apiLoginService = apiLoginService;
    }

    public void setApiService() {
        this.apiService = new ApiProvider().provideApiService();
    }
}
