package com.anfly.anflyshop.presenter.sort;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.sort.SortConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.CatalogIndexBean;
import com.anfly.anflyshop.utils.RxUtils;

public class SortPresenter extends BasePresenter<SortConstract.View> implements SortConstract.Presenter {
    @Override
    public void getSortData() {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .catalogIndex()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<CatalogIndexBean>(mView) {
                    @Override
                    public void onNext(CatalogIndexBean catalogIndexBean) {
                        if (catalogIndexBean.getErrno() == 0) {
                            mView.getSortResponse(catalogIndexBean);
                        } else {
                            super.onNext(catalogIndexBean);
                        }
                    }
                }));
    }
}
