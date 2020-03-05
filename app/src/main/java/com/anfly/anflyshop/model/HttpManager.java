package com.anfly.anflyshop.model;

import android.util.Log;

import com.anfly.anflyshop.common.Constants;
import com.anfly.anflyshop.model.apis.AnflyServer;
import com.anfly.anflyshop.utils.SharedPreferencesUtil;
import com.anfly.anflyshop.utils.SystemUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .addInterceptor(new LogginInterceptor())
                .addInterceptor(new HeadersInterceptor())
                .addNetworkInterceptor(new NetWorkInterceptor())
                .cache(cache)
                .build();
    }

    /**
     * 日志拦截器，打印请求接口的报文信息
     */
    static class LogginInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            //1.请求前--打印请求信息
            long startTime = System.nanoTime();
            Log.d("TAG", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            //2.网络请求
            Response response = chain.proceed(request);

            //3.网络响应后--打印响应信息
            long endTime = System.nanoTime();
            Log.d("TAG", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (endTime - startTime) / 1e6d, response.headers()));

            return response;
        }
    }

    /**
     * 请求的修改设置
     */
    public static class HeadersInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Connection", "keep-alive")//使成为长连接
                    .addHeader("Content-Type", "ANDROID")//区分我是什么端
                    .addHeader("X-Nideshop-Token", (String) SharedPreferencesUtil.getInstance().getParam(Constants.TOKEN,""))//传一个token，用户的唯一表示，一般为动态返回到客户端
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 网络拦截器，为了做缓存
     */
    public static class NetWorkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!SystemUtils.checkNetWork()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if (!SystemUtils.checkNetWork()) {
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge).build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, onlyif-cached, max-stale=" + maxStale).build();
            }
        }
    }


}
