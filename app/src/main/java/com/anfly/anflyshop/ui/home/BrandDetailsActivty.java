package com.anfly.anflyshop.ui.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.IBasePresenter;
import com.anfly.anflyshop.interfaces.home.BrandDetailsConstract;
import com.anfly.anflyshop.model.bean.BrandDetailsBean;
import com.anfly.anflyshop.presenter.home.BrandDetailsPresenter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandDetailsActivty extends BaseActivity<BrandDetailsConstract.Presenter> implements BrandDetailsConstract.View {
    @BindView(R.id.iv_brand)
    ImageView ivBrand;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_details;
    }

    @Override
    protected BrandDetailsPresenter createPresenter() {
        return new BrandDetailsPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getBundleExtra("data");
        int id = bundle.getInt("id");
        presener.getBrandDetaisData(id);
    }

    @Override
    public void getBrandDetailsResponse(BrandDetailsBean brandDetailsBean) {
        BrandDetailsBean.DataBean.BrandBean brand = brandDetailsBean.getData().getBrand();
        Glide.with(this).load(brand.getList_pic_url()).into(ivBrand);
        tvDes.setText(brand.getSimple_desc());
        tvName.setText(brand.getName());
    }
}
