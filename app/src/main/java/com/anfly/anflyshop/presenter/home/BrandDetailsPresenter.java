package com.anfly.anflyshop.presenter.home;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.home.BrandDetailsConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.BrandDetailsBean;
import com.anfly.anflyshop.utils.RxUtils;

public class BrandDetailsPresenter extends BasePresenter<BrandDetailsConstract.View> implements BrandDetailsConstract.Presenter {

    @Override
    public void getBrandDetaisData(int id) {
        addSubscribe(HttpManager.getInstance().getAnflyServer().brandDetails(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<BrandDetailsBean>(mView) {
                    @Override
                    public void onNext(BrandDetailsBean brandDetailsBean) {
                        if (brandDetailsBean.getErrno() == 0) {
                            mView.getBrandDetailsResponse(brandDetailsBean);
                        } else {
                            super.onNext(brandDetailsBean);
                        }
                    }
                }));
    }
}
