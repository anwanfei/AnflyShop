package com.anfly.anflyshop.interfaces.home;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.HomeBean;

import java.util.List;

/**
 * 契约类，为了实现
 *  高内聚：HomeConstract
 *  低耦合：Presenter和view内部关联
 *
 */
public interface HomeConstract {
    interface View extends IBaseView {
        void getHomeDataReturn(HomeBean result);

        void getHomeDataResponse(List<HomeBean.HomeListBean> list);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHomeData();
    }
}
