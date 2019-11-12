package com.cts.avin.ui.main;

import android.os.Bundle;

import com.cts.avin.R;
import com.cts.avin.base.BaseActivity;

public class Home_Activity extends BaseActivity {
    @Override
    protected int layoutRes() {
        return R.layout.home_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addFragment( AboutList_Fragment.newInstance(), R.id.container);
        }
    }
}
