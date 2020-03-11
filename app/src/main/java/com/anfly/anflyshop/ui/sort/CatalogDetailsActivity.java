package com.anfly.anflyshop.ui.sort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.sort.CatalogDetailsConstract;
import com.anfly.anflyshop.model.bean.GoodscategoryBean;
import com.anfly.anflyshop.presenter.sort.CatalogDetailsPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CatalogDetailsActivity extends BaseActivity<CatalogDetailsConstract.Presenter> implements CatalogDetailsConstract.View {

    @BindView(R.id.tab_catalog_details)
    TabLayout tabCatalogDetails;
    @BindView(R.id.vp_catalog_details)
    ViewPager vpCatalogDetails;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private int position;

    @Override
    protected int getLayout() {
        return R.layout.activity_catalog_details;
    }

    @Override
    protected CatalogDetailsConstract.Presenter createPresenter() {
        return new CatalogDetailsPresenter();
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getBundleExtra("data");
        int id = bundle.getInt("id");
        position = bundle.getInt("position");
        presener.getCataLogDetailsData(id);
    }

    @Override
    public void getCataLogDetailsResponse(GoodscategoryBean goodscategoryBean) {
        List<GoodscategoryBean.DataBean.BrotherCategoryBean> brotherCategory = goodscategoryBean.getData().getBrotherCategory();
        for (int i = 0; i < brotherCategory.size(); i++) {
            GoodscategoryBean.DataBean.BrotherCategoryBean brotherCategoryBean = brotherCategory.get(i);
            fragments.add(new CatalogDetailsFragment(brotherCategoryBean.getId(), brotherCategoryBean.getName(), brotherCategoryBean.getFront_name()));
            titles.add(brotherCategoryBean.getName());
        }
        VpCatalogDetailsAdapter vpCatalogDetailsAdapter = new VpCatalogDetailsAdapter(getSupportFragmentManager(), fragments, titles);
        vpCatalogDetails.setAdapter(vpCatalogDetailsAdapter);
        tabCatalogDetails.setupWithViewPager(vpCatalogDetails);
        tabCatalogDetails.getTabAt(position).select();
    }
}
