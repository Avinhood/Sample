package com.cts.avin.di.modules;

import com.cts.avin.ui.main.Home_Activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract Home_Activity contributeActivityInjector();
}
