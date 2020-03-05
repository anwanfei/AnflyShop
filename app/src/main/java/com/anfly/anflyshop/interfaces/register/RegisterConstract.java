package com.anfly.anflyshop.interfaces.register;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.RegisterBean;

public interface RegisterConstract {

    interface View extends IBaseView {
        void getRegisterResponse(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void register(String nickname, String password, String verify);
    }
}
