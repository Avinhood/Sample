package com.cts.avin.network;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.cts.avin.data.main.ListData;
import com.cts.avin.util.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class APIRepoRepository {



    private String TAG = "APIRepoRepository";

    private final ApiInterface apiService;

    @Inject
    public APIRepoRepository(ApiInterface api) {
        apiService = api;

    }

//    private Retrofit getRetrofit() {
//
//        if(mRetrofit==null){
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(provideOfflineCacheInterceptor())
//                    .addNetworkInterceptor(provideCacheInterceptor())
//                    .cache(provideCache());
//
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            mOkHttpClient = httpClient.build();
//
//            mRetrofit = new Retrofit.Builder()
//                    .baseUrl(Constant.BASE_URL)
//                    .client(mOkHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return mRetrofit;
//    }
//
//
//    public Retrofit getCachedRetrofit() {
//        if (mCachedRetrofit == null) {
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                    // Add all interceptors you want (headers, URL, logging)
//                    .addInterceptor(provideForcedOfflineCacheInterceptor())
//                    .cache(provideCache());
//
//            mCachedOkHttpClient = httpClient.build();
//
//            mCachedRetrofit = new Retrofit.Builder()
//                    .baseUrl(Constant.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                    .client(mCachedOkHttpClient)
//                    .build();
//        }
//
//        return mCachedRetrofit;
//    }
//
//    private Interceptor provideForcedOfflineCacheInterceptor() {
//        return chain -> {
//            Request request = chain.request();
//
//            CacheControl cacheControl = new CacheControl.Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build();
//
//            request = request.newBuilder()
//                    .removeHeader(HEADER_PRAGMA)
//                    .removeHeader(HEADER_CACHE_CONTROL)
//                    .cacheControl(cacheControl)
//                    .build();
//
//            return chain.proceed(request);
//        };
//    }
    public Single<ListData> getMainList() {
        return apiService.getMainList();
    }






    // =============================================================
    public interface ApiInterface {

        @GET(Constant.LIST_URL)
        Single<ListData> getMainList();

    }

}
