package com.anfly.anflyshop.presenter.search;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.search.SearchResultContract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.SearchResultBean;
import com.anfly.anflyshop.utils.RxUtils;

public class SearchResultPresenter extends BasePresenter<SearchResultContract.View> implements SearchResultContract.Presenter {
    @Override
    public void getSearchResultData(String keyword, int page, int size, String sort, String order, int categoryId) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .searchResultList(keyword, page, size, sort, order, categoryId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<SearchResultBean>(mView) {
                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        if (searchResultBean.getErrno() == 0) {
                            mView.getSearchResultResponse(searchResultBean);
                        } else {
                            super.onNext(searchResultBean);
                        }
                    }
                }));
    }
}
