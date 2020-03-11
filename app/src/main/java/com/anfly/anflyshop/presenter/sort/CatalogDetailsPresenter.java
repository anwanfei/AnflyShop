package com.anfly.anflyshop.presenter.sort;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.sort.CatalogDetailsConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.GoodscategoryBean;
import com.anfly.anflyshop.utils.RxUtils;

public class CatalogDetailsPresenter extends BasePresenter<CatalogDetailsConstract.View> implements CatalogDetailsConstract.Presenter {
    @Override
    public void getCataLogDetailsData(int id) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .goodsCategory(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<GoodscategoryBean>(mView) {
                    @Override
                    public void onNext(GoodscategoryBean goodscategoryBean) {
                        if (goodscategoryBean.getErrno() == 0) {
                            mView.getCataLogDetailsResponse(goodscategoryBean);
                        } else {
                            super.onNext(goodscategoryBean);
                        }
                    }
                }));
    }
}
