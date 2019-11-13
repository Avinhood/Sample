package com.cts.avin.di.modules;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cts.avin.di.util.ViewModelKey;
import com.cts.avin.ui.main.Home_Activity;
import com.cts.avin.util.ViewModelFactory;
import com.cts.avin.viewmodel.AboutList_ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class FeatureModule {
    @Binds
    @IntoMap
    @ViewModelKey(AboutList_ViewModel.class)
    abstract ViewModel bindMainListViewModel(AboutList_ViewModel mainViewModel);
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract Home_Activity contributeActivityInjector();

    @Binds
    abstract Context provideContext(Application application);

}
