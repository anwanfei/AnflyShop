package com.anfly.anflyshop.interfaces.sort;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.GoodsCatalogListBean;

public interface CatalogDetailsListConstact {
    interface View extends IBaseView {
        void getCataLogDetailsListResponse(GoodsCatalogListBean goodsCatalogListBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCataLogDetailsListData(int id, int page, int size);
    }
}
