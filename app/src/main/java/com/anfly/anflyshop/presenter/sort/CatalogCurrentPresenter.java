package com.anfly.anflyshop.presenter.sort;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.sort.CatalogCurrentConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;
import com.anfly.anflyshop.utils.RxUtils;

public class CatalogCurrentPresenter extends BasePresenter<CatalogCurrentConstract.View> implements CatalogCurrentConstract.Presenter {
    @Override
    public void getCatalogCurrentData(int id) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .catalogCurrent(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<CatalogCurrentBean>(mView) {
                    @Override
                    public void onNext(CatalogCurrentBean catalogCurrentBean) {
                        if (catalogCurrentBean.getErrno() == 0) {
                            mView.getCatalogCurrentResponse(catalogCurrentBean);
                        } else {
                            super.onNext(catalogCurrentBean);
                        }
                    }
                }));
    }
}
