package com.cts.avin.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cts.avin.R;
import com.cts.avin.base.BaseActivity;
import com.cts.avin.ui.main.MainFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.main_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addFragment( MainFragment.newInstance(), R.id.container);
        }
    }
}
