package com.anfly.anflyshop.common;

import com.anfly.anflyshop.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 统一处理返回结果
 */
public abstract class ResponseSubcriber<T> extends ResourceSubscriber<T> {

    private IBaseView view;

    public ResponseSubcriber(IBaseView view) {
        this.view = view;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {
        if (view != null) {
            view.showTips(t.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
