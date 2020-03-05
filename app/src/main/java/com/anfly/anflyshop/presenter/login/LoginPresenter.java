package com.anfly.anflyshop.presenter.login;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.login.LoginConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.LoginBean;
import com.anfly.anflyshop.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter {
    @Override
    public void logig(String nickName, String password) {
        addSubscribe(HttpManager.getInstance().getAnflyServer().login(nickName, password)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean != null) {
                            mView.loginReeurn(loginBean);
                        } else {
                            super.onNext(loginBean);
                        }
                    }
                }));
    }
}
