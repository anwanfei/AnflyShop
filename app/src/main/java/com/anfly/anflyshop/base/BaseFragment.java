package com.anfly.anflyshop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类，相当于抽取v层的实现基类
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    //如果P层的类型，使用IBasePresenter
    //定义P泛型的变量
    protected P presener;
    //当前页面的context
    protected Context context;
    //当前页面的activity
    protected Activity activity;
    private Unbinder unbinder;

    /**
     * 用来加载布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        //container/attachToRoot参数设置避免约束布局的时候布局显示不完整
        View view = LayoutInflater.from(context).inflate(getLayout(), container, false);
        return view;
    }

    /**
     * 布局创建完成之后做的一系列操作
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presener = createPresenter();
        //关联view
        if (presener != null) {
            presener.onAttachView(this);
        }

        initView();
        initData();
    }

    /**
     * 获取当前界面的布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 创建p层实例
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Override
    public void showTips(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presener != null) {
            presener.onDettachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
