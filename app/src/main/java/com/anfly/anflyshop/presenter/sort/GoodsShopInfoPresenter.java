package com.anfly.anflyshop.presenter.sort;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.sort.GoodsShopInfoConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.GoodsShopDetailBean;
import com.anfly.anflyshop.utils.RxUtils;

public class GoodsShopInfoPresenter extends BasePresenter<GoodsShopInfoConstract.View> implements GoodsShopInfoConstract.Presenter {
    @Override
    public void getGoodsShopInfoData(int id) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .goodsDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<GoodsShopDetailBean>(mView) {
                    @Override
                    public void onNext(GoodsShopDetailBean goodsShopDetailBean) {
                        if (goodsShopDetailBean.getErrno() == 0) {
                            mView.getGoodsShopInfoResponse(goodsShopDetailBean);
                        } else {
                            super.onNext(goodsShopDetailBean);
                        }
                    }
                }));
    }
}
