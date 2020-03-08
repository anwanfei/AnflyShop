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

public class HomeNewGoodsAdapter extends BaseDelegateAdapter {

    public HomeNewGoodsAdapter(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_new_goods;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {
        HomeBean.DataBean.NewGoodsListBean bean = (HomeBean.DataBean.NewGoodsListBean) data;
        ImageView iv_item_brand = (ImageView) holder.getViewById(R.id.iv_item_brand);
        TextView tv_brand_name = (TextView) holder.getViewById(R.id.tv_brand_name);
        TextView tv_brand_price = (TextView) holder.getViewById(R.id.tv_brand_price);

        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_brand);
        tv_brand_name.setText(bean.getName());
        tv_brand_price.setText("¥" + bean.getRetail_price());


    }
}
