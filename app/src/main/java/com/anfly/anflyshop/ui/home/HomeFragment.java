package com.anfly.anflyshop.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseDelegateAdapter;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.common.Constants;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.presenter.home.HomePresenter;
import com.anfly.anflyshop.ui.home.adapter.BannerAdapter;
import com.anfly.anflyshop.ui.home.adapter.ChannelAdapter;
import com.anfly.anflyshop.ui.home.adapter.HomeBrandAdapter;
import com.anfly.anflyshop.ui.home.adapter.HomeCategoryAdapter;
import com.anfly.anflyshop.ui.home.adapter.HomeHotGoodsAdsapterr;
import com.anfly.anflyshop.ui.home.adapter.HomeNewGoodsAdapter;
import com.anfly.anflyshop.ui.home.adapter.HomeTitleAdapter;
import com.anfly.anflyshop.ui.home.adapter.HomeTopicAdaper;
import com.anfly.anflyshop.ui.webview.WebviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presener.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        final HomeBean.DataBean data = result.getData();

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rv.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(context);
        rv.setLayoutManager(layoutManager);
        DelegateAdapter adapters = new DelegateAdapter(layoutManager, true);

        //添加banner,使用SingleLayoutHelper通栏布局，只会显示一个组件View
        List<HomeBean.DataBean.BannerBean> banners = data.getBanner();
        SingleLayoutHelper singHelper = new SingleLayoutHelper();
        singHelper.setBgColor(R.color.colorPrimary);
        singHelper.setMargin(0, 0, 0, 0);
        adapters.addAdapter(new BannerAdapter(singHelper, banners));

        //channel频道：ColumnLayoutHelper,栏格布局，都布局在一排，可以配置不同列之间的宽度比值
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setMargin(0, 0, 0, 0);
        ChannelAdapter channelAdapter = new ChannelAdapter(context, data.getChannel(), columnLayoutHelper);
        adapters.addAdapter(channelAdapter);
        channelAdapter.setOnItemClickLIstener(new BaseDelegateAdapter.OnItemClickLIstener() {
            @Override
            public void onItemClick(BaseDelegateAdapter.BaseDelegateViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.URL, data.getChannel().get(position).getUrl());
                goToActivity(WebviewActivity.class, bundle);
            }
        });

        //品牌制造商直供标题，使用SingleLayoutHelper通栏布局，只会显示一个组件View
        ArrayList<String> titles = new ArrayList<>();
        titles.add("品牌制造商直供");
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        adapters.addAdapter(new HomeTitleAdapter(context, titles, singleLayoutHelper));

        //品牌制造商直供列表，GridLayoutHelper: Grid布局， 支持横向的colspan
        final List<HomeBean.DataBean.BrandListBean> brandList = data.getBrandList();
        GridLayoutHelper gridHelper = new GridLayoutHelper(2);
        gridHelper.setMarginTop(5);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridHelper.setVGap(2);
        //设置水平方向条目的间隔
        gridHelper.setHGap(2);
        gridHelper.setMarginLeft(0);
        gridHelper.setMarginBottom(0);
        //自动填充满布局
        gridHelper.setAutoExpand(true);
        HomeBrandAdapter homeBrandAdapter = new HomeBrandAdapter(context, brandList, gridHelper);
        adapters.addAdapter(homeBrandAdapter);
        homeBrandAdapter.setOnItemClickLIstener(new BaseDelegateAdapter.OnItemClickLIstener() {
            @Override
            public void onItemClick(BaseDelegateAdapter.BaseDelegateViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", brandList.get(position).getId());
                goToActivity(BrandDetailsActivty.class, bundle);
            }
        });


        //新品首发标题，使用SingleLayoutHelper通栏布局，只会显示一个组件View
        ArrayList<String> newGoodsTitles = new ArrayList<>();
        newGoodsTitles.add("周一周四 - 新品首发");
        adapters.addAdapter(new HomeTitleAdapter(context, newGoodsTitles, singleLayoutHelper));

        //新品首发列表，GridLayoutHelper: Grid布局， 支持横向的colspan
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setMarginTop(5);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridLayoutHelper.setVGap(2);
        //设置水平方向条目的间隔
        gridLayoutHelper.setHGap(2);
        gridLayoutHelper.setMarginLeft(0);
        gridLayoutHelper.setMarginBottom(0);
        //自动填充满布局
        gridLayoutHelper.setAutoExpand(true);
        adapters.addAdapter(new HomeNewGoodsAdapter(context, data.getNewGoodsList(), gridLayoutHelper));

        //人气推荐标题，使用SingleLayoutHelper通栏布局，只会显示一个组件View
        ArrayList<String> hotGoodstitles = new ArrayList<>();
        hotGoodstitles.add("人气推荐");
        adapters.addAdapter(new HomeTitleAdapter(context, hotGoodstitles, singleLayoutHelper));

        //人气推荐：使用 线性布局LinearLayoutHelper
        LinearLayoutHelper hotGoodsHelper = new LinearLayoutHelper(10);
        adapters.addAdapter(new HomeHotGoodsAdsapterr(context, data.getHotGoodsList(), hotGoodsHelper));

        //专题标题，使用SingleLayoutHelper通栏布局，只会显示一个组件View
        ArrayList<String> topictitles = new ArrayList<>();
        topictitles.add("专题精选");
        adapters.addAdapter(new HomeTitleAdapter(context, topictitles, singleLayoutHelper));

        //专题列表
        List<HomeBean.DataBean.TopicListBean> topicList = data.getTopicList();
        GridLayoutHelper topicHelper = new GridLayoutHelper(2);
        topicHelper.setMarginTop(5);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        topicHelper.setVGap(2);
        //设置水平方向条目的间隔
        topicHelper.setHGap(2);
        topicHelper.setMarginLeft(0);
        topicHelper.setMarginBottom(10);
        //自动填充满布局
        topicHelper.setAutoExpand(true);
        HomeTopicAdaper homeTopicAdaper = new HomeTopicAdaper(context, topicList, topicHelper);
        adapters.addAdapter(homeTopicAdaper);
        homeTopicAdaper.setOnItemClickLIstener(new BaseDelegateAdapter.OnItemClickLIstener() {
            @Override
            public void onItemClick(BaseDelegateAdapter.BaseDelegateViewHolder holder, int position) {

            }
        });

        //分类
        List<HomeBean.DataBean.CategoryListBean> categoryList = data.getCategoryList();

        for (int i = 0; i < categoryList.size(); i++) {
            //分类标题
            ArrayList<String> categoryTitles = new ArrayList<>();
            categoryTitles.add(categoryList.get(i).getName());
            adapters.addAdapter(new HomeTitleAdapter(context, categoryTitles, singleLayoutHelper));

            //分类列表
            GridLayoutHelper categoryHelper = new GridLayoutHelper(2);
            categoryHelper.setMarginTop(5);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
            //设置垂直方向条目的间隔
            categoryHelper.setVGap(2);
            //设置水平方向条目的间隔
            categoryHelper.setHGap(2);
            categoryHelper.setMarginLeft(0);
            categoryHelper.setMarginBottom(10);
            //自动填充满布局
            categoryHelper.setAutoExpand(true);
            adapters.addAdapter(new HomeCategoryAdapter(context, categoryList.get(i).getGoodsList(), categoryHelper));
        }
        rv.setAdapter(adapters);
    }
}
