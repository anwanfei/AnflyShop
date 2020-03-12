package com.anfly.anflyshop.ui.home.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.utils.GlideImageLoader;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class NewHomeAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {
    private ArrayList<HomeBean.HomeListBean> list;

    public NewHomeAdapter(ArrayList<HomeBean.HomeListBean> list) {
        super(list);
        this.list = list;
        addItemType(HomeBean.HomeListBean.TYPE_BANNER, R.layout.item_banner_layout);
        addItemType(HomeBean.HomeListBean.TYPE_CHANNEL, R.layout.item_home_channel_layout);
        addItemType(HomeBean.HomeListBean.TYPE_TITLE, R.layout.item_home_brand_title_layout);
        addItemType(HomeBean.HomeListBean.TYPE_BRAND, R.layout.item_home_brand);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_NEWGOOD, R.layout.item_home_new_goods);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_HOTGOOD, R.layout.item_home_hot_goods);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_TOPIC, R.layout.layout_topic_recy);
        addItemType(HomeBean.HomeListBean.TYPE_CATEGORY, R.layout.item_home_category);
        addItemType(HomeBean.HomeListBean.TYPE_VIEW_LINE, R.layout.layout_view_line);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()) {
            case HomeBean.HomeListBean.TYPE_BANNER:
                //banner放入到条目中会出现显示不出来
                //initBanner(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_CHANNEL:
                initChannel(helper, item);
                break;
            case HomeBean.HomeListBean.TYPE_BRAND:
                initBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TITLE:
                initTitle(helper, item);
                break;
            case HomeBean.HomeListBean.TYPE_NEWGOOD:
                initNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_HOTGOOD:
                initHotGood(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TOPIC:
                initTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_CATEGORY:
                initCategory(helper, (HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_VIEW_LINE:

                break;
        }
    }

    //初始化banner
    private void initBanner(BaseViewHolder helper, HomeBean.HomeListBean data) {
        Banner banner = helper.getView(R.id.banner);
        if (TextUtils.isEmpty(banner.tag)) {
            List<String> imgs = new ArrayList<>();
            for (HomeBean.DataBean.BannerBean item : (List<HomeBean.DataBean.BannerBean>) data.data) {
                imgs.add(item.getImage_url());
            }
            banner.tag = "true";
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imgs);
            banner.start();
        }
    }

    //初始化channel
    private void initChannel(BaseViewHolder helper, HomeBean.HomeListBean data) {
    }

    //初始化title
    private void initTitle(BaseViewHolder helper, HomeBean.HomeListBean data) {
        helper.setText(R.id.tv_title, data.title);
    }

    //初始化品牌
    private void initBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.iv_item_brand));
        helper.setText(R.id.tv_brand_name, data.getName());
        helper.setText(R.id.tv_brand_price, "￥" + data.getFloor_price());
    }

    //初始化新商品数据
    private void initNewGood(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.iv_item_brand));
        helper.setText(R.id.tv_brand_name, data.getName());
        helper.setText(R.id.tv_brand_price, "￥" + data.getRetail_price());
    }

    //初始化热门商品
    private void initHotGood(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.iv_item_hop_goods));
        helper.setText(R.id.tv_title_hot_goods, data.getName());
        helper.setText(R.id.tv_sub_title_hot_goods, data.getGoods_brief());
        helper.setText(R.id.tv_hot_goods_price, "￥" + data.getRetail_price());
    }

    //初始化专题
    private void initTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView recyclerView = helper.getView(R.id.recy_topic);
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            TopicAdapter topicAdapter = new TopicAdapter(R.layout.layout_item_topic, data);
            topicAdapter.bindToRecyclerView(recyclerView);
        }
    }

    //初始化category
    private void initCategory(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.iv_item_category));
        helper.setText(R.id.tv_category_name, data.getName());
        helper.setText(R.id.tv_category_price, "￥" + data.getRetail_price());
    }

}
