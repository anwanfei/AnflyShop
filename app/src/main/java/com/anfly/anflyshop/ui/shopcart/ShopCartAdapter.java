package com.anfly.anflyshop.ui.shopcart;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShopCartAdapter extends BaseAdapter {
    public ShopCartAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_shop_cart;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        CartIndexBean.DataBean.CartListBean cartListBean = (CartIndexBean.DataBean.CartListBean) data;
        TextView tv_name = (TextView) holder.getViewById(R.id.tv_name);
        TextView tv_price = (TextView) holder.getViewById(R.id.tv_price);
        TextView tv_num_shop = (TextView) holder.getViewById(R.id.tv_num_shop);
        ImageView iv_add_shop_cart = (ImageView) holder.getViewById(R.id.iv_add_shop_cart);
        Glide.with(mContext).load(cartListBean.getList_pic_url()).into(iv_add_shop_cart);
        tv_name.setText(cartListBean.getGoods_name());
        tv_price.setText("¥" + cartListBean.getRetail_price());
        tv_num_shop.setText("×" + cartListBean.getNumber());
    }
}