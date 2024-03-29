package com.cts.avin.di.component;

import android.app.Application;

import com.cts.avin.base.BaseApplication;
import com.cts.avin.di.modules.AppModule;
import com.cts.avin.di.modules.FeatureModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;


@Component(modules = { AppModule.class, AndroidSupportInjectionModule.class, FeatureModule.class})
public interface AppComponent  extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
