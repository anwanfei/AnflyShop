package com.anfly.anflyshop.ui.specialtopic;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
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
    private List<SpecialTopicBean.DataBeanX.DataBean> list;
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
        adapter = new SpecialTopicAdapter(list, context);
        rv.setAdapter(adapter);

        adapter.setOnItemClickLIstener(new BaseAdapter.OnItemClickLIstener() {
            @Override
            public void onItemClick(BaseAdapter.BaseViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(position).getId());
                goToActivity(SpecialTopicActivity.class, bundle);
            }
        });

        //响应条目中组件的点击事件
        adapter.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_title_topic:
                        SpecialTopicBean.DataBeanX.DataBean dataBean = (SpecialTopicBean.DataBeanX.DataBean) v.getTag();
                        Toast.makeText(context, dataBean.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_topic_des:
                        SpecialTopicBean.DataBeanX.DataBean dataBean1 = (SpecialTopicBean.DataBeanX.DataBean) v.getTag();
                        Toast.makeText(context, dataBean1.getSubtitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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
