package com.cts.avin.di.modules;

import com.cts.avin.ui.main.AboutList_Fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    public abstract AboutList_Fragment provideMainFragment();
}
