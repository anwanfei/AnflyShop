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

public class HomeHotGoodsAdsapterr extends BaseDelegateAdapter {
    public HomeHotGoodsAdsapterr(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_hot_goods;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {
        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) data;
        ImageView iv_item_hop_goods = (ImageView) holder.getViewById(R.id.iv_item_hop_goods);
        TextView tv_title_hot_goods = (TextView) holder.getViewById(R.id.tv_title_hot_goods);
        TextView tv_sub_title_hot_goods = (TextView) holder.getViewById(R.id.tv_sub_title_hot_goods);
        TextView tv_hot_goods_price = (TextView) holder.getViewById(R.id.tv_hot_goods_price);

        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_hop_goods);
        tv_title_hot_goods.setText(bean.getName());
        tv_sub_title_hot_goods.setText(bean.getGoods_brief());
        tv_hot_goods_price.setText("¥" + bean.getRetail_price());

    }
}
