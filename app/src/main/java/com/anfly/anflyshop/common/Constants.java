package com.anfly.anflyshop.common;

import com.anfly.anflyshop.app.AnflyApplication;

import java.io.File;

public interface Constants {
    String PATH_DATA = AnflyApplication.getAnflyApplication().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = PATH_DATA + "/shopapp";

    //商城的基础地址
    String BASE_SHOP_URL = "https://cdwan.cn/api/";
    String TOKEN = "token";
    String URL = "url";
    String NICKNAME = "nickname";
}
