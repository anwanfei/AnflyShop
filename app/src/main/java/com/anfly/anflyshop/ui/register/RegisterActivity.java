package com.anfly.anflyshop.ui.register;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.register.RegisterConstract;
import com.anfly.anflyshop.model.bean.RegisterBean;
import com.anfly.anflyshop.presenter.register.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterConstract.Presenter> implements RegisterConstract.View {

    @BindView(R.id.et_user2)
    EditText etUser2;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_confirm)
    EditText etPwdConfirm;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterConstract.Presenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void getRegisterResponse(RegisterBean registerBean) {
        int errno = registerBean.getErrno();
        if (errno == 0) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if (errno == 100) {
            Toast.makeText(RegisterActivity.this, registerBean.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        register();
    }

    private void register() {
        String name = etUser2.getText().toString();
        String pwd = etPwd.getText().toString();
        String verify = etPwdConfirm.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(verify) && pwd.equals(verify)) {
            presener.register(name, pwd, verify);
        }
    }
}
