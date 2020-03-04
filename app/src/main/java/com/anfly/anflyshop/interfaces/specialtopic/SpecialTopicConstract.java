package com.anfly.anflyshop.interfaces.specialtopic;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;

/**
 * 专题数据
 */
public interface SpecialTopicConstract {

    interface View extends IBaseView {
        void getTopicDataResponse(SpecialTopicBean specialTopicBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getTopicData(int page, int size);
    }

}
