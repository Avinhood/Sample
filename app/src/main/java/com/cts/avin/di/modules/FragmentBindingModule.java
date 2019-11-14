package com.cts.avin.di.modules;

import com.cts.avin.ui.main.AboutListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    public abstract AboutListFragment provideMainFragment();
}
