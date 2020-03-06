package com.anfly.anflyshop.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private LayoutHelper mHelper;
    private List<HomeBean.DataBean.BannerBean> banners;

    public BannerAdapter(LayoutHelper mHelper, List<HomeBean.DataBean.BannerBean> banners) {
        this.mHelper = mHelper;
        this.banners = banners;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItemHolder recyclerViewHolder = (RecyclerViewItemHolder) holder;
        //设置图片集合
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            images.add(banners.get(i).getImage_url());
            titles.add(banners.get(i).getName());
        }
        recyclerViewHolder.banner
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setBannerTitles(titles)
                .setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 正常条目的item的ViewHolder
     */
    private class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        public Banner banner;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}
