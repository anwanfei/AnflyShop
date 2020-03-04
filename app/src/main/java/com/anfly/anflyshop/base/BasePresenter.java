package com.anfly.anflyshop.base;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * P层基类
 *
 * @param <V>
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    //当前关联过来的v层的类
    protected V mView;

    //采用弱引用关联view，如果直接使用会遇到问题（内存泄漏）
    WeakReference<V> weakReference;

    //Rxjava2背压式处理内存,网络数据进出不一致
    CompositeDisposable compositeDisposable;


    @Override
    public void onAttachView(V v) {
        weakReference = new WeakReference(v);
        mView = weakReference.get();
    }


    @Override
    public void onDettachView() {
        mView = null;
        //取消关联的时候，也就是页面切走的时候清楚调当前页面的网络请求
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }


    /**
     * 添加请求数据的对象到背压式管理类CompositeDisposable
     *
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

}
