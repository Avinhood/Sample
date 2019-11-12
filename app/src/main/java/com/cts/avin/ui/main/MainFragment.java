package com.cts.avin.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cts.avin.R;
import com.cts.avin.adapter.MainListAdapter;
import com.cts.avin.base.BaseApplication;
import com.cts.avin.base.BaseFragment;
import com.cts.avin.data.main.ListData;
import com.cts.avin.data.main.Rows;
import com.cts.avin.listener.ItemSelectedListener;
import com.cts.avin.util.Constant;
import com.cts.avin.util.Util;
import com.cts.avin.util.ViewModelFactory;
import com.cts.avin.viewmodel.MainListViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends BaseFragment implements ItemSelectedListener  {

    private final String TAG  = MainFragment.class.getName();

    @BindView(R.id.recyclerView)
    RecyclerView mListView;
    @BindView(R.id.tv_error)
    TextView mErrorTextView;
    @BindView(R.id.loading_view)
    View mLoadingView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    @Inject
    ViewModelFactory mViewModelFactory;
    private MainListViewModel mViewModel;

    private ListData mMainListData;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.main_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainListViewModel.class);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeMainListCall();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipe_refresh.setRefreshing(false);
                    }

                }, 2000);
            }
        });

        swipe_refresh.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.list_name));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        mListView.setAdapter(new MainListAdapter(mViewModel, this, this));

//        if (isNetworkConnected()) {
        if (mMainListData == null) {
            makeMainListCall();
        } else {
            mLoadingView.setVisibility(View.GONE);
        }
//        }
//        else {
//            Toast.makeText(getContext(), getString(R.string.internet_error_msg), Toast.LENGTH_SHORT).show();
//        }
    }

    /*
     * Method to call the ViewModel which will hit the API & will give the response.
     * */
    protected void makeMainListCall() {

        mViewModel.getProgressDialog().observe(this, isLoading -> {
            if (isLoading != null) {
                mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    mErrorTextView.setVisibility(View.GONE);
                    mListView.setVisibility(View.GONE);
                }
            }
        });
        mViewModel.getMainListData().observe(this, new Observer<ListData>() {
            @Override
            public void onChanged(@Nullable ListData listData) {
                getActivity().setTitle(listData.getTitle());
                mMainListData = listData;
                mErrorTextView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
            }
        });

        mViewModel.getErrorMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mErrorTextView.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
            }
        });

        mViewModel.makeMainListCall();
    }

    @Override
    public void onItemSelected(Rows data) {
        Log.d(TAG, "selected item is : "+data.getTitle());
    }
}
