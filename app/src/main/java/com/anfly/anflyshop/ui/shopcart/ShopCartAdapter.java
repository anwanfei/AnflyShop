package com.anfly.anflyshop.ui.shopcart;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShopCartAdapter extends BaseAdapter {

    private String state = "编辑";
    private boolean isShowCheckbox = true;

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
        TextView tv_reduce = (TextView) holder.getViewById(R.id.tv_reduce);
        TextView tv_total_num = (TextView) holder.getViewById(R.id.tv_total_num);
        TextView tv_add = (TextView) holder.getViewById(R.id.tv_add);
        CheckBox cb_item = (CheckBox) holder.getViewById(R.id.cb_item);
        ImageView iv_add_shop_cart = (ImageView) holder.getViewById(R.id.iv_add_shop_cart);
        ConstraintLayout cl_edit = (ConstraintLayout) holder.getViewById(R.id.cl_edit);

        if (isShowCheckbox) {
            cb_item.setVisibility(View.VISIBLE);
        } else {
            cb_item.setVisibility(View.GONE);
        }

        //根据  状态  设置条目的 控件的显示和隐藏
        if (state.equals("编辑")) {
            tv_total_num.setVisibility(View.VISIBLE);
            cl_edit.setVisibility(View.GONE);

        } else {
            tv_total_num.setVisibility(View.GONE);
            cl_edit.setVisibility(View.VISIBLE);
        }

        Glide.with(mContext).load(cartListBean.getList_pic_url()).into(iv_add_shop_cart);
        tv_name.setText(cartListBean.getGoods_name());
        tv_price.setText("¥" + cartListBean.getRetail_price());
        tv_total_num.setText("×" + cartListBean.getNumber());
        cb_item.setChecked(cartListBean.isChecked());

//        cb_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                cartListBean.setChecked(isChecked);
//                cb_item.setChecked(isChecked);
//                EventBus.getDefault().post("layoutPosition");
//            }
//        });
    }

    //改变条目控件  状态值    编辑  完成
    public void setItemVisibility(String state, boolean isShowCheckBox) {
        this.state = state;
        this.isShowCheckbox = isShowCheckBox;
        notifyDataSetChanged();
    }
}
