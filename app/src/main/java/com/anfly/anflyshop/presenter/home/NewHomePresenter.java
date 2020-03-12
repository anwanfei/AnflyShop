package com.anfly.anflyshop.presenter.home;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class NewHomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter {
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .getHome()
                .compose(RxUtils.rxScheduler())
                .map(new Function<HomeBean, List<HomeBean.HomeListBean>>() {
                    @Override
                    public List<HomeBean.HomeListBean> apply(HomeBean homeBean) throws Exception {
                        ArrayList<HomeBean.HomeListBean> list = new ArrayList<>();
                        HomeBean.DataBean bean = homeBean.getData();

                        //banner
                        HomeBean.HomeListBean banner = new HomeBean.HomeListBean();
                        banner.currentType = HomeBean.HomeListBean.TYPE_BANNER;
                        banner.data = bean.getBanner();
                        list.add(banner);

                        //channel
                        HomeBean.HomeListBean channel = new HomeBean.HomeListBean();
                        channel.currentType = HomeBean.HomeListBean.TYPE_CHANNEL;
                        channel.data = bean.getChannel();
                        list.add(channel);

                        //分割线
                        HomeBean.HomeListBean splitLine = new HomeBean.HomeListBean();
                        splitLine.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(splitLine);

                        //brand title
                        HomeBean.HomeListBean brandTitle = new HomeBean.HomeListBean();
                        brandTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        brandTitle.title = "品牌商直供";
                        list.add(brandTitle);

                        //brand
                        for (HomeBean.DataBean.BrandListBean brandListBean : bean.getBrandList()) {
                            HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                            brand.currentType = HomeBean.HomeListBean.TYPE_BRAND;
                            brand.data = brandListBean;
                            list.add(brand);
                        }

                        //new good title
                        HomeBean.HomeListBean newGoodTitle = new HomeBean.HomeListBean();
                        newGoodTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        newGoodTitle.title = "周一周四 · 新品首发";
                        list.add(newGoodTitle);

                        //new goods
                        for (HomeBean.DataBean.NewGoodsListBean newGoodsListBean : bean.getNewGoodsList()) {
                            HomeBean.HomeListBean newGood = new HomeBean.HomeListBean();
                            newGood.currentType = HomeBean.HomeListBean.TYPE_NEWGOOD;
                            newGood.data = newGoodsListBean;
                            list.add(newGood);
                        }

                        //分割线
                        HomeBean.HomeListBean lineHot = new HomeBean.HomeListBean();
                        lineHot.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineHot);

                        //hottitle
                        HomeBean.HomeListBean hotTitle = new HomeBean.HomeListBean();
                        hotTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        hotTitle.title = "人气推荐";
                        list.add(hotTitle);

                        // hot good
                        for (HomeBean.DataBean.HotGoodsListBean hotGoodsListBean : bean.getHotGoodsList()) {
                            HomeBean.HomeListBean hotGood = new HomeBean.HomeListBean();
                            hotGood.currentType = HomeBean.HomeListBean.TYPE_HOTGOOD;
                            hotGood.data = hotGoodsListBean;
                            list.add(hotGood);
                        }

                        //分割线
                        HomeBean.HomeListBean lineTopic = new HomeBean.HomeListBean();
                        lineTopic.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineTopic);

                        //topictitle
                        HomeBean.HomeListBean topicTitle = new HomeBean.HomeListBean();
                        topicTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        topicTitle.title = "专题精选";
                        list.add(topicTitle);

                        //topic
                        HomeBean.HomeListBean topic = new HomeBean.HomeListBean();
                        topic.currentType = HomeBean.HomeListBean.TYPE_TOPIC;
                        topic.data = bean.getTopicList();
                        list.add(topic);

                        //分割线
                        HomeBean.HomeListBean lineCategory = new HomeBean.HomeListBean();
                        lineCategory.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineCategory);

                        //category
                        for (HomeBean.DataBean.CategoryListBean categoryListBean : bean.getCategoryList()) {

                            //category title
                            HomeBean.HomeListBean category = new HomeBean.HomeListBean();
                            category.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                            category.title = categoryListBean.getName();
                            list.add(category);

                            //category list
                            for (HomeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean : categoryListBean.getGoodsList()) {
                                HomeBean.HomeListBean categoryList = new HomeBean.HomeListBean();
                                categoryList.currentType = HomeBean.HomeListBean.TYPE_CATEGORY;
                                categoryList.data = goodsListBean;
                                list.add(categoryList);
                            }

                            //分割线
                            HomeBean.HomeListBean line = new HomeBean.HomeListBean();
                            line.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                            list.add(line);
                        }
                        return list;
                    }
                })
                .subscribeWith(new ResponseSubcriber<List<HomeBean.HomeListBean>>(mView) {
                    @Override
                    public void onNext(List<HomeBean.HomeListBean> list) {
                        mView.getHomeDataResponse(list);
                    }
                }));
    }
}
