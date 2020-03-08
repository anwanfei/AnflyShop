package com.anfly.anflyshop.interfaces.home;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.BrandDetailsBean;

public interface BrandDetailsConstract {
    public interface View extends IBaseView {
        void getBrandDetailsResponse(BrandDetailsBean brandDetailsBean);
    }

    public interface Presenter extends IBasePresenter<View> {
        void getBrandDetaisData(int id);
    }
}
