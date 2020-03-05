package com.anfly.anflyshop.presenter.register;

import com.anfly.anflyshop.base.BasePresenter;
import com.anfly.anflyshop.common.ResponseSubcriber;
import com.anfly.anflyshop.interfaces.register.RegisterConstract;
import com.anfly.anflyshop.model.HttpManager;
import com.anfly.anflyshop.model.bean.RegisterBean;
import com.anfly.anflyshop.utils.RxUtils;

public class RegisterPresenter extends BasePresenter<RegisterConstract.View> implements RegisterConstract.Presenter {
    @Override
    public void register(String nickname, String password, String verify) {
        addSubscribe(HttpManager.getInstance().getAnflyServer()
                .register(nickname, password, verify)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubcriber<RegisterBean>(mView) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean != null) {
                            mView.getRegisterResponse(registerBean);
                        } else {
                            super.onNext(registerBean);
                        }
                    }
                }));
    }
}
