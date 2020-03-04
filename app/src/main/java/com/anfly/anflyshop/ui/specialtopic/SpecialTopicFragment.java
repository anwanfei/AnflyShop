package com.anfly.anflyshop.ui.specialtopic;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.specialtopic.SpecialTopicConstract;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.anfly.anflyshop.presenter.SpecialTopic.SpecialTopicPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SpecialTopicFragment extends BaseFragment<SpecialTopicConstract.Presenter> implements SpecialTopicConstract.View, OnRefreshLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private ArrayList<SpecialTopicBean.DataBeanX.DataBean> list;
    private SpecialTopicAdapter adapter;
    private int page = 1;
    private int size = 10;
    private int totalPages = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_special_topic;
    }

    @Override
    protected SpecialTopicConstract.Presenter createPresenter() {
        return new SpecialTopicPresenter();
    }


    @Override
    protected void initView() {

        rv.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        adapter = new SpecialTopicAdapter(context, list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        srl.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        presener.getTopicData(page, size);
    }

    @Override
    public void getTopicDataResponse(SpecialTopicBean specialTopicBean) {
        totalPages = specialTopicBean.getData().getPageSize();
        List<SpecialTopicBean.DataBeanX.DataBean> data = specialTopicBean.getData().getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();
        srl.finishLoadMore();
        srl.finishRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        if (page <= totalPages) {
            initData();
        } else {
            Toast.makeText(context, "到底了！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        if (list != null && list.size() > 0) {
            list.clear();
            initData();
        }
    }
}
