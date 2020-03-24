package com.anfly.anflyshop.interfaces.search;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.SearchResultBean;

public interface SearchResultContract {
    interface View extends IBaseView {
        void getSearchResultResponse(SearchResultBean searchResultBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getSearchResultData(String keyword, int page, int size, String sort, String order, int categoryId);
    }
}
