package com.anfly.anflyshop.ui.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseDelegateAdapter;

import java.util.List;

public class HomeTitleAdapter extends BaseDelegateAdapter {


    public HomeTitleAdapter(Context mContext, List data, LayoutHelper layoutHelper) {
        super(mContext, data, layoutHelper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_brand_title_layout;
    }

    @Override
    protected void bindData(BaseDelegateViewHolder holder, Object data) {
        String name = (String) data;
        TextView tv_title = (TextView) holder.getViewById(R.id.tv_title);
        tv_title.setText(name);
    }
}
