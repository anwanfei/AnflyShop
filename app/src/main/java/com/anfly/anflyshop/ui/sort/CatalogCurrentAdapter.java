package com.anfly.anflyshop.ui.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class CatalogCurrentAdapter extends BaseAdapter {

    public CatalogCurrentAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_catalog_current;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        CatalogCurrentBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = (CatalogCurrentBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        ImageView iv_catalogcurrent_item = (ImageView) holder.getViewById(R.id.iv_catalogcurrent_item);
        TextView tv_catalogcurrent_item = (TextView) holder.getViewById(R.id.tv_catalogcurrent_item);
        Glide.with(mContext).load(bean.getWap_banner_url()).into(iv_catalogcurrent_item);
        tv_catalogcurrent_item.setText(bean.getName());
    }
}
