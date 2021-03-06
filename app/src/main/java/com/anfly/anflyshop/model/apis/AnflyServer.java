package com.anfly.anflyshop.model.apis;

import com.anfly.anflyshop.model.bean.BrandDetailsBean;
import com.anfly.anflyshop.model.bean.CardAddBean;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;
import com.anfly.anflyshop.model.bean.CatalogIndexBean;
import com.anfly.anflyshop.model.bean.GoodsCatalogListBean;
import com.anfly.anflyshop.model.bean.GoodsShopDetailBean;
import com.anfly.anflyshop.model.bean.GoodscategoryBean;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.model.bean.LoginBean;
import com.anfly.anflyshop.model.bean.RegisterBean;
import com.anfly.anflyshop.model.bean.SearchResultBean;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.anfly.anflyshop.model.bean.TopicDetailBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AnflyServer {

    /**
     * 使用背压式接受结果
     */
    @GET("index")
    Flowable<HomeBean> getHome();

    @GET("topic/list")
    Flowable<SpecialTopicBean> getTopic(@Query("page") int page, @Query("size") int size);

    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("nickname") String nickname, @Field("password") String password);

    @POST("auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("nickname") String nickname, @Field("password") String password, @Field("verify") String verify);

    @GET("brand/detail")
    Flowable<BrandDetailsBean> brandDetails(@Query("id") int id);

    @GET("catalog/index")
    Flowable<CatalogIndexBean> catalogIndex();

    @GET("catalog/current")
    Flowable<CatalogCurrentBean> catalogCurrent(@Query("id") int id);

    @GET("goods/category")
    Flowable<GoodscategoryBean> goodsCategory(@Query("id") int id);

    @GET("goods/list")
    Flowable<GoodsCatalogListBean> goodsList(@Query("id") int id, @Query("page") int page, @Query("size") int size);

    @GET("topic/detail")
    Flowable<TopicDetailBean> topicDetail(@Query("id") int id);

    @GET("goods/detail")
    Flowable<GoodsShopDetailBean> goodsDetail(@Query("id") int id);

    @GET("cart/index")
    Flowable<CartIndexBean> cartIndex();

    @GET("goods/list")
    Flowable<SearchResultBean> searchResultList(@Query("keyword") String keyword, @Query("page") int page
            , @Query("size") int size, @Query("sort") String sort, @Query("order") String order, @Query("categoryId") int categoryId);

    @POST("cart/add")
    @FormUrlEncoded
    Flowable<CardAddBean> cardAdd(@Field("goodsId") int goodsId, @Field("productId") int productId, @Field("number") int number);
}
