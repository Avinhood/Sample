package com.cts.avin.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cts.avin.R;
import com.cts.avin.adapter.AboutList_Adapter;
import com.cts.avin.base.BaseFragment;
import com.cts.avin.data.main.ListData;
import com.cts.avin.data.main.Rows;
import com.cts.avin.adapter.AboutListItemSelected_Listener;
import com.cts.avin.util.ViewModelFactory;
import com.cts.avin.viewmodel.AboutList_ViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class AboutList_Fragment extends BaseFragment implements AboutListItemSelected_Listener {

    private final String TAG  = AboutList_Fragment.class.getName();

    @BindView(R.id.recyclerView)
    RecyclerView mListView;
    @BindView(R.id.tv_error)
    TextView mErrorTextView;
    @BindView(R.id.loading_view)
    View mLoadingView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Inject
    ViewModelFactory mViewModelFactory;
    private AboutList_ViewModel mViewModel;
    private ListData mMainListData;

    public static AboutList_Fragment newInstance() {
        return new AboutList_Fragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.aboutlist_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AboutList_ViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeMainListCall();
            }
        });

        mSwipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        mListView.setAdapter(new AboutList_Adapter(mViewModel, this, this));

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
                mSwipeRefresh.setRefreshing(false);
                getActivity().setTitle(listData.getTitle());
                mMainListData = listData;
                mErrorTextView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
            }
        });

        mViewModel.getErrorMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mSwipeRefresh.setRefreshing(false);
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
