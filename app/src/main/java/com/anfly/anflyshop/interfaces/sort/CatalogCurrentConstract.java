package com.anfly.anflyshop.interfaces.sort;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;

public interface CatalogCurrentConstract {
    interface View extends IBaseView {
        void getCatalogCurrentResponse(CatalogCurrentBean catalogCurrentBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCatalogCurrentData(int id);
    }
}
