package com.anfly.anflyshop.interfaces.sort;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.GoodsShopDetailBean;

public interface GoodsShopInfoConstract {
    interface View extends IBaseView {
        void getGoodsShopInfoResponse(GoodsShopDetailBean goodsShopDetailBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodsShopInfoData(int id);
    }
}
