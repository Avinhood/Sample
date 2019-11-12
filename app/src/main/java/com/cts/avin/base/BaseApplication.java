package com.cts.avin.base;

import android.app.Application;
import android.app.Activity;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import com.bumptech.glide.Glide;
import com.cts.avin.di.component.AppComponent;
import com.cts.avin.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {



    private Glide glide;
    @Override
    public void onCreate() {
        super.onCreate();
        glide = Glide.get(this);
    }

    public Glide getGlide() {
        return glide;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
