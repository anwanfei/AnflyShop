package com.anfly.anflyshop.ui.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseDelegateAdapter;
import com.anfly.anflyshop.model.bean.HomeBean;

import java.util.List;

public class ChannelAdapter extends BaseDelegateAdapter {


    public ChannelAdapter(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_channel_layout;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {
        HomeBean.DataBean.ChannelBean bean = (HomeBean.DataBean.ChannelBean) data;
        TextView tv_channel = (TextView) holder.getViewById(R.id.tv_channel);
        tv_channel.setText(bean.getName());
    }
}
