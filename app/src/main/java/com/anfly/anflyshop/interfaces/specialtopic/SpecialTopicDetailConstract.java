package com.anfly.anflyshop.interfaces.specialtopic;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.TopicDetailBean;

public interface SpecialTopicDetailConstract {
    interface View extends IBaseView {
        void getSpecialTopicInfoResponse(TopicDetailBean topicDetailBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getTopicDetailData(int id);
    }
}
