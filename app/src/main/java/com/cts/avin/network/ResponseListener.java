package com.cts.avin.network;

public interface ResponseListener<T> {
    void onSuccess(T t);

    void onError(String msg);
}
