package com.cts.avin.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.cts.avin.network.APIRepoRepository;
import com.cts.avin.data.main.ListData;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainListViewModel extends BaseViewModel{

    private final MutableLiveData<ListData> mainList = new MutableLiveData<>();
    private final APIRepoRepository repoRepository;
    private CompositeDisposable disposable;

    @Inject
    public MainListViewModel(APIRepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    /*
     * Method to get get the List by hitting API.
     * */
    public void makeMainListCall() {
        Log.e("===", "makeMainListCall");
        getProgressDialog().postValue(true);
        disposable.add(repoRepository.getMainList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ListData>() {
                    @Override
                    public void onSuccess(ListData listData) {
                        mainList.postValue(listData);
                        getProgressDialog().postValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getProgressDialog().postValue(false);
                        getErrorMsg().postValue("");
                    }
                }));
    }


    public MutableLiveData<ListData> getMainListData() {
        return mainList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}
