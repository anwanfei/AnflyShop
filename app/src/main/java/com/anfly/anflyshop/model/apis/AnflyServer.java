package com.anfly.anflyshop.model.apis;

import com.anfly.anflyshop.model.bean.BrandDetailsBean;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;
import com.anfly.anflyshop.model.bean.CatalogIndexBean;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.model.bean.LoginBean;
import com.anfly.anflyshop.model.bean.RegisterBean;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;

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
}
