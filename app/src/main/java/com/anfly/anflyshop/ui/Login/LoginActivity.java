package com.anfly.anflyshop.ui.Login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.common.Constants;
import com.anfly.anflyshop.interfaces.login.LoginConstract;
import com.anfly.anflyshop.model.bean.LoginBean;
import com.anfly.anflyshop.presenter.login.LoginPresenter;
import com.anfly.anflyshop.ui.register.RegisterActivity;
import com.anfly.anflyshop.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View {

    @BindView(R.id.et_user2)
    EditText etUser2;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginConstract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    private void login() {
        String name = etUser2.getText().toString();
        String pwd = etPwd.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            presener.logig(name, pwd);
        }
    }

    @Override
    public void loginReeurn(LoginBean loginBean) {
        String token = loginBean.getData().getToken();
        SharedPreferencesUtil.getInstance().setParam(Constants.TOKEN, token);
        finish();
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                goRegister();
                break;
            case R.id.tv_forget_pwd:
                break;
        }
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
