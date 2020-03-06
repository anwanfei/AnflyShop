package com.anfly.anflyshop.ui.specialtopic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class SpecialTopicAdapter extends BaseAdapter {

    public SpecialTopicAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_special_topic;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        SpecialTopicBean.DataBeanX.DataBean dataBean = (SpecialTopicBean.DataBeanX.DataBean) data;
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
