package com.anfly.anflyshop.presenter.SpecialTopic;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.specialtopic.SpecialTopicConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.anfly.anflyshop.utils.RxUtils;

public class SpecialTopicPresenter extends BasePresenter<SpecialTopicConstract.View> implements SpecialTopicConstract.Presenter {
    @Override
    public void getTopicData(int page, int size) {
        addSubscribe(HttpManager.getInstance().getAnflyServer().getTopic(page, size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<SpecialTopicBean>(mView) {
                    @Override
                    public void onNext(SpecialTopicBean specialTopicBean) {
                        mView.getTopicDataResponse(specialTopicBean);
                    }
                }));
    }

}
