package com.cts.avin.network;

import com.cts.avin.data.main.ListData;
import com.cts.avin.util.Constant;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.http.GET;


public class ApiService {
    private String TAG = "ApiService";
    private final ApiInterface apiService;
    @Inject
    public ApiService(ApiInterface api) {
        apiService = api;
    }

    public Single<ListData> getMainList() {
        return apiService.getMainList();
    }

    // =============================================================
    public interface ApiInterface {
        @GET(Constant.LIST_URL)
        Single<ListData> getMainList();
    }

}
