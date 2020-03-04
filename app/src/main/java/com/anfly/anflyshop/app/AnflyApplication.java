package com.anfly.anflyshop.app;

import android.app.Application;

public class AnflyApplication extends Application {

    public static AnflyApplication anflyApplication;

    /**
     * 提供全局上下文
     *
     * @return
     */
    public static AnflyApplication getAnflyApplication() {
        return anflyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        anflyApplication = this;
    }
}
