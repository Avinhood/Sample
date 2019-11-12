package com.cts.avin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    MutableLiveData<Boolean> progressDialog = new MutableLiveData<>();
    MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public MutableLiveData<Boolean> getProgressDialog() {
        return progressDialog;
    }

    public MutableLiveData<String> getErrorMsg() {
        return errorMsg;
    }
}
