package com.cts.avin.di.component;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.cts.avin.base.BaseApplication;
import com.cts.avin.di.modules.ActivityBindingModule;
import com.cts.avin.di.modules.AppModule;
import com.cts.avin.di.modules.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;


@Component(modules = {ContextModule.class, AppModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface AppComponent  extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
