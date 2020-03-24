package com.anfly.anflyshop.ui.shopcart;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.model.bean.CartIndexBean;

import java.util.ArrayList;

import butterknife.BindView;

public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price_name)
    TextView tvPriceName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;
    @BindView(R.id.rv_confirm)
    RecyclerView rvConfirm;
    @BindView(R.id.tv_real_price)
    TextView tvRealPrice;
    private float totalPrice = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        Bundle data = getIntent().getBundleExtra("data");
        ArrayList<CartIndexBean.DataBean.CartListBean> list = (ArrayList<CartIndexBean.DataBean.CartListBean>) data.getSerializable("list");
        for (CartIndexBean.DataBean.CartListBean cartListBean : list) {
            totalPrice += cartListBean.getRetail_price();
        }
        tvPrice.setText("¥" + totalPrice);
        tvRealPrice.setText("实付：¥" + totalPrice);
        ShopCartAdapter adapter = new ShopCartAdapter(list, this);
        adapter.setItemVisibility("编辑", false);
        rvConfirm.setLayoutManager(new LinearLayoutManager(this));
        rvConfirm.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

}
