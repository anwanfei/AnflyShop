package com.anfly.anflyshop.presenter.shopcart;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.shopcart.ShopCartConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.anfly.anflyshop.utils.RxUtils;

public class ShopCartPresenter extends BasePresenter<ShopCartConstract.View> implements ShopCartConstract.Presenter {
    @Override
    public void getCartIndexData() {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .cartIndex()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<CartIndexBean>(mView) {
                    @Override
                    public void onNext(CartIndexBean cartIndexBean) {
                        if (cartIndexBean != null && cartIndexBean.getErrno() == 0) {
                            mView.getCartIndexResponse(cartIndexBean);
                        } else {
                            super.onNext(cartIndexBean);
                        }
                    }
                }));
    }
}
