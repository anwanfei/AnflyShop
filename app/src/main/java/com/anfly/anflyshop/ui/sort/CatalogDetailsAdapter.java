package com.anfly.anflyshop.ui.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.GoodsCatalogListBean;
import com.bumptech.glide.Glide;

import java.util.List;

class CatalogDetailsAdapter extends BaseAdapter {

    public CatalogDetailsAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_category_details;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {

        GoodsCatalogListBean.DataBeanX.GoodsListBean bean = (GoodsCatalogListBean.DataBeanX.GoodsListBean) data;

        ImageView iv_item_category = (ImageView) holder.getViewById(R.id.iv_item_category);
        TextView tv_category_name = (TextView) holder.getViewById(R.id.tv_category_name);
        TextView tv_category_price = (TextView) holder.getViewById(R.id.tv_category_price);

        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_category);
        tv_category_name.setText(bean.getName());
        tv_category_price.setText("¥" + bean.getRetail_price());
    }
}
