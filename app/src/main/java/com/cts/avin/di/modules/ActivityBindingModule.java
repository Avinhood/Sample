package com.cts.avin.di.modules;

import com.cts.avin.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity contributeActivityInjector();
}
