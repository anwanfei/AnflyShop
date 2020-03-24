package com.anfly.anflyshop.ui.search;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.SearchResultBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    public SearchAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_search;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        SearchResultBean.DataBeanX.DataBean bean = (SearchResultBean.DataBeanX.DataBean) data;

        ImageView iv_item_search = (ImageView) holder.getViewById(R.id.iv_item_search);
        TextView tv_search_name = (TextView) holder.getViewById(R.id.tv_search_name);
        TextView tv_search_price = (TextView) holder.getViewById(R.id.tv_search_price);

        tv_search_name.setText(bean.getName());
        tv_search_price.setText("¥" + bean.getRetail_price());
        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_search);
    }
}
