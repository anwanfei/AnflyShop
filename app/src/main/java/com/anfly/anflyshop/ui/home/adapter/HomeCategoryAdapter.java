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

public class HomeCategoryAdapter extends BaseDelegateAdapter {
    public HomeCategoryAdapter(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_category;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {

        HomeBean.DataBean.CategoryListBean.GoodsListBean bean = (HomeBean.DataBean.CategoryListBean.GoodsListBean) data;

        ImageView iv_item_category = (ImageView) holder.getViewById(R.id.iv_item_category);
        TextView tv_category_name = (TextView) holder.getViewById(R.id.tv_category_name);
        TextView tv_category_price = (TextView) holder.getViewById(R.id.tv_category_price);

        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_category);
        tv_category_name.setText(bean.getName());
        tv_category_price.setText("¥" + bean.getRetail_price());
    }
}
