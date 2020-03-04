package com.anfly.anflyshop.model;

import com.anfly.anflyshop.common.Constants;
import com.anfly.anflyshop.model.apis.AnflyServer;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装网络请求的类
 * retrofit+Rxjava
 */
public class HttpManager {

    //网络接口对象
    private AnflyServer anflyServer;
    private static volatile HttpManager instance;

    /**
     * 双检锁单例，避免出现高并发，其实Android出现的可能性很小
     * 为了严谨，以及继承Java的开发思想
     *
     * @return
     */
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取网络接口对象
     *
     * @return
     */
    public AnflyServer getAnflyServer() {
        if (anflyServer == null) {
            synchronized (AnflyServer.class) {
                if (anflyServer == null) {
                    anflyServer = getRetrofit(Constants.BASE_SHOP_URL).create(AnflyServer.class);
                }
            }
        }
        return anflyServer;
    }


    /**
     * 获取retrofit实例
     *
     * @param baseUrl
     */
    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * 封装网络请求的Okhttpclient对象
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        File file = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(file, 100 * 1024 * 1024);
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }
}
