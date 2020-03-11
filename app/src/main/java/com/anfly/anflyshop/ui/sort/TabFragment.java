package com.anfly.anflyshop.ui.sort;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.sort.CatalogCurrentConstract;
import com.anfly.anflyshop.model.bean.CatalogCurrentBean;
import com.anfly.anflyshop.presenter.sort.CatalogCurrentPresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends BaseFragment<CatalogCurrentConstract.Presenter> implements CatalogCurrentConstract.View, BaseAdapter.OnItemClickLIstener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.iv_wap_banner)
    ImageView ivWapBanner;
    @BindView(R.id.tv_frot_name)
    TextView tvFrotName;
    @BindView(R.id.tv_catalog)
    TextView tvCatalog;
    private ArrayList<CatalogCurrentBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryListBeans;
    private CatalogCurrentAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab;
    }

    @Override
    protected CatalogCurrentConstract.Presenter createPresenter() {
        return new CatalogCurrentPresenter();
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new GridLayoutManager(context, 3));
        subCategoryListBeans = new ArrayList<>();

        adapter = new CatalogCurrentAdapter(subCategoryListBeans, context);
        rv.setAdapter(adapter);

        adapter.setOnItemClickLIstener(this);
    }

    @Override
    protected void initData() {
        int id = (int) getArguments().get("id");
        presener.getCatalogCurrentData(id);
    }

    @Override
    public void getCatalogCurrentResponse(CatalogCurrentBean catalogCurrentBean) {
        CatalogCurrentBean.DataBean.CurrentCategoryBean currentCategory = catalogCurrentBean.getData().getCurrentCategory();
        Glide.with(context).load(currentCategory.getWap_banner_url()).into(ivWapBanner);
        tvCatalog.setText("—— " + currentCategory.getName() + "分类 ——");
        tvFrotName.setText(currentCategory.getFront_name());
        subCategoryListBeans.addAll(currentCategory.getSubCategoryList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseAdapter.BaseViewHolder holder, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",subCategoryListBeans.get(position).getId());
        bundle.putInt("position",position);
        goToActivity(CatalogDetailsActivity.class,bundle);
    }
}
