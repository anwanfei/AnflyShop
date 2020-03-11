package com.anfly.anflyshop.presenter.sort;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.sort.CatalogDetailsListConstact;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.GoodsCatalogListBean;
import com.anfly.anflyshop.utils.RxUtils;

public class CatalogDetailsListPresenter extends BasePresenter<CatalogDetailsListConstact.View> implements CatalogDetailsListConstact.Presenter {
    @Override
    public void getCataLogDetailsListData(int id, int page, int size) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .goodsList(id, page, size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<GoodsCatalogListBean>(mView) {
                    @Override
                    public void onNext(GoodsCatalogListBean goodsCatalogListBean) {
                        if (goodsCatalogListBean.getErrno() == 0) {
                            mView.getCataLogDetailsListResponse(goodsCatalogListBean);
                        } else {
                            super.onNext(goodsCatalogListBean);
                        }
                    }
                }));
    }
}
