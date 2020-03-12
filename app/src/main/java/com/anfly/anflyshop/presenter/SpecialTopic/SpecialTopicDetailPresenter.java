package com.anfly.anflyshop.presenter.SpecialTopic;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.specialtopic.SpecialTopicDetailConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.TopicDetailBean;
import com.anfly.anflyshop.utils.RxUtils;

public class SpecialTopicDetailPresenter extends BasePresenter<SpecialTopicDetailConstract.View> implements SpecialTopicDetailConstract.Presenter {
    @Override
    public void getTopicDetailData(int id) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .topicDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<TopicDetailBean>(mView) {
                    @Override
                    public void onNext(TopicDetailBean topicDetailBean) {
                        if (topicDetailBean.getErrno() == 0) {
                            mView.getSpecialTopicInfoResponse(topicDetailBean);
                        } else {
                            super.onNext(topicDetailBean);
                        }
                    }
                }));
    }
}
