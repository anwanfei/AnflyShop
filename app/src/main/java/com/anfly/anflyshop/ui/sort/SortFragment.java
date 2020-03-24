package com.anfly.anflyshop.ui.sort;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.sort.SortConstract;
import com.anfly.anflyshop.model.bean.CatalogIndexBean;
import com.anfly.anflyshop.presenter.sort.SortPresenter;
import com.anfly.anflyshop.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.QTabView;

public class SortFragment extends BaseFragment<SortConstract.Presenter> implements SortConstract.View {


    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.activity_tab_fragment)
    LinearLayout activityTabFragment;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected SortConstract.Presenter createPresenter() {
        return new SortPresenter();
    }

    @Override
    protected void initView() {

    }

    private List<Fragment> getFragments(List<CatalogIndexBean.DataBean.CategoryListBean> size) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < size.size(); i++) {
            TabFragment fragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", size.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    @Override
    protected void initData() {
        presener.getSortData();
    }

    @Override
    public void getSortResponse(CatalogIndexBean catalogIndexBean) {
        List<CatalogIndexBean.DataBean.CategoryListBean> categoryList = catalogIndexBean.getData().getCategoryList();
        List<Fragment> fragments = getFragments(categoryList);
        tablayout.setupWithFragment(getChildFragmentManager(), R.id.fragment_container, fragments
                , new TabAdapter() {
                    @Override
                    public int getCount() {
                        return categoryList.size();
                    }

                    @Override
                    public QTabView.TabBadge getBadge(int position) {
                        return null;
                    }

                    @Override
                    public QTabView.TabIcon getIcon(int position) {
                        return null;
                    }

                    @Override
                    public QTabView.TabTitle getTitle(int position) {
                        return new QTabView.TabTitle.Builder().setContent(categoryList.get(position).getName()).build();
                    }

                    @Override
                    public int getBackground(int position) {
                        return 0;
                    }
                });
        tablayout.setTabSelected(0);
    }

    @OnClick(R.id.ll_search)
    public void onViewClicked() {
        goToActivity(SearchActivity.class, null);
    }
}
