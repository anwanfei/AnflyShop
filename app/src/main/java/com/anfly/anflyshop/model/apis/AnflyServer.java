package com.anfly.anflyshop.model.apis;

import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnflyServer {

    /**
     * 使用背压式接受结果
     */
    @GET("index")
    Flowable<HomeBean> getHome();

    @GET("topic/lis")
    Flowable<SpecialTopicBean> getTopic(@Query("page") int page, @Query("size") int si
    );
}
