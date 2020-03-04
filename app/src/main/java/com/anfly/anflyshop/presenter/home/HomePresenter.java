package com.anfly.anflyshop.presenter.home;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.utils.RxUtils;

public class HomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter {
    @Override
    public void getHomeData() {
        //请求主界面数据
        addSubscribe(HttpManager.getInstance().getAnflyServer().getHome()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<HomeBean>(mView) {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean.getErrno() == 0) {
                            mView.getHomeDataReturn(homeBean);
                        } else {
                            super.onNext(homeBean);
                        }
                    }
                }));

    }
}
