package com.cts.avin.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }

    /**
     * Add a fragment to the given container.
     *
     * @param fragment
     * @param containerId
     */
    public void addFragment(Fragment fragment, @IdRes int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}
