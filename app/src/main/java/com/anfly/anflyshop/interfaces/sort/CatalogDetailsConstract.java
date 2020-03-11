package com.anfly.anflyshop.interfaces.sort;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.GoodscategoryBean;

public interface CatalogDetailsConstract {
    interface View extends IBaseView {
        void getCataLogDetailsResponse(GoodscategoryBean goodscategoryBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCataLogDetailsData(int id);
    }
}
