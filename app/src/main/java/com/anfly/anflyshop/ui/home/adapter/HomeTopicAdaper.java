package com.anfly.anflyshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseDelegateAdapter;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeTopicAdaper extends BaseDelegateAdapter {
    public HomeTopicAdaper(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_topic;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {
        HomeBean.DataBean.TopicListBean dataBean = (HomeBean.DataBean.TopicListBean) data;

        ImageView iv_topic = (ImageView) holder.getViewById(R.id.iv_topic);
        TextView tv_title_topic = (TextView) holder.getViewById(R.id.tv_title_topic);
        TextView tv_topic_des = (TextView) holder.getViewById(R.id.tv_topic_des);
        TextView tv_topic_price = (TextView) holder.getViewById(R.id.tv_topic_price);

        Glide.with(mContext).load(dataBean.getScene_pic_url()).into(iv_topic);
        tv_title_topic.setText(dataBean.getTitle());
        tv_topic_des.setText(dataBean.getSubtitle());
        tv_topic_price.setText(String.valueOf(dataBean.getPrice_info()));
    }
}
