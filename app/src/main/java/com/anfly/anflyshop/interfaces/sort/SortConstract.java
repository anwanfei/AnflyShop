package com.anfly.anflyshop.interfaces.sort;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.CatalogIndexBean;

public interface SortConstract {
    interface View extends IBaseView {
        void getSortResponse(CatalogIndexBean catalogIndexBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getSortData();
    }
}
