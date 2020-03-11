package com.anfly.anflyshop.ui.sort;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.sort.CatalogDetailsListConstact;
import com.anfly.anflyshop.model.bean.GoodsCatalogListBean;
import com.anfly.anflyshop.presenter.sort.CatalogDetailsListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogDetailsFragment extends BaseFragment<CatalogDetailsListConstact.Presenter> implements CatalogDetailsListConstact.View {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_front_name)
    TextView tvFrontName;
    private int id;
    private String name, front_name;
    private ArrayList<GoodsCatalogListBean.DataBeanX.GoodsListBean> list;
    private CatalogDetailsAdapter adapter;

    public CatalogDetailsFragment() {
    }

    public CatalogDetailsFragment(int id, String name, String front_name) {
        this.id = id;
        this.name = name;
        this.front_name = front_name;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_catalog_details;
    }

    @Override
    protected CatalogDetailsListConstact.Presenter createPresenter() {
        return new CatalogDetailsListPresenter();
    }

    @Override
    protected void initView() {

        tvFrontName.setText(front_name);
        tvName.setText(name);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        list = new ArrayList<>();
        adapter = new CatalogDetailsAdapter(list, context);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presener.getCataLogDetailsListData(id, 1, 10);
    }

    @Override
    public void getCataLogDetailsListResponse(GoodsCatalogListBean goodsCatalogListBean) {
        List<GoodsCatalogListBean.DataBeanX.GoodsListBean> goodsList = goodsCatalogListBean.getData().getGoodsList();
        list.addAll(goodsList);
        adapter.notifyDataSetChanged();
    }
}
