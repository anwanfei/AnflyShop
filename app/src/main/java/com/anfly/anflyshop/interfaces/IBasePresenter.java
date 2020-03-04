package com.anfly.anflyshop.interfaces;

/**
 * 定义presenter接口基类
 * 满足做view层以接口的形式关联
 *
 * @param <V>:T是用来接受view的,是一个受限泛型或者有界泛型
 */
public interface IBasePresenter<V extends IBaseView> {
    /**
     * 关联view层到P层
     *
     * @param v
     */
    void onAttachView(V v);

    /**
     * 解除view层关联
     */
    void onDettachView();
}
