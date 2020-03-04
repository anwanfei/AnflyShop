package com.anfly.anflyshop.ui.specialtopic;

import android.util.Log;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.specialtopic.SpecialTopicConstract;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.anfly.anflyshop.presenter.SpecialTopic.SpecialTopicPresenter;

public class SpecialTopicFragment extends BaseFragment<SpecialTopicConstract.Presenter> implements SpecialTopicConstract.View {

    @Override
    protected int getLayout() {
        return R.layout.fragment_special_topic;
    }

    @Override
    protected SpecialTopicConstract.Presenter createPresenter() {
        return new SpecialTopicPresenter();
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presener.getTopicData(1, 100);
    }

    @Override
    public void getTopicDataResponse(SpecialTopicBean specialTopicBean) {
        Log.e("TAG", "SpecialTopicBean"+specialTopicBean.toString());
    }
}
