package com.anfly.anflyshop.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类，相当于抽取v层的实现基类
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    //如果P层的类型，使用IBasePresenter
    //定义P泛型的变量
    protected P presener;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        //本来是可以通过构造方法去传递view层的，但是构造不能被继承，所以只能使用可以被重写的方法去关联view
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
    protected void onDestroy() {
        super.onDestroy();
        if(presener!=null) {
            presener.onDettachView();
        }
        if(unbinder!=null) {
            unbinder.unbind();
        }
    }
}
