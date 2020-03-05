package com.anfly.anflyshop.interfaces.login;

import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.IBaseView;
import com.anfly.anflyshop.model.bean.LoginBean;

public interface LoginConstract {
    interface View extends IBaseView {
        void loginReeurn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void logig(String nickName, String password);
    }
}
