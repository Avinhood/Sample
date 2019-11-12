package com.cts.avin.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cts.avin.di.util.ViewModelKey;
import com.cts.avin.util.ViewModelFactory;
import com.cts.avin.viewmodel.MainListViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainListViewModel.class)
    abstract ViewModel bindMainListViewModel(MainListViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
