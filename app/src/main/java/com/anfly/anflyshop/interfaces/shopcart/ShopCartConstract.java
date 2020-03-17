package com.anfly.anflyshop.interfaces.shopcart;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.CartIndexBean;

public interface ShopCartConstract {
    interface View extends IBaseView {
        void getCartIndexResponse(CartIndexBean cartIndexBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCartIndexData();
    }
}
