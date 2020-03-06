package com.anfly.anflyshop.ui.home;


import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.presenter.home.HomePresenter;
import com.anfly.anflyshop.ui.home.adapter.BannerAdapter;
import com.anfly.anflyshop.ui.home.adapter.ChannelHelperAdapter;
import com.anfly.anflyshop.ui.home.adapter.SingleLayoutAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presener.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        List<HomeBean.DataBean.BannerBean> banners = result.getData().getBanner();

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rv.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(context);

        rv.setLayoutManager(layoutManager);

        DelegateAdapter adapters = new DelegateAdapter(layoutManager, true);

        //添加banner
        SingleLayoutHelper singHelper = new SingleLayoutHelper();
        singHelper.setBgColor(R.color.colorPrimary);
        singHelper.setMargin(0, 0, 0, 0);
        adapters.addAdapter(new BannerAdapter(singHelper, banners));

//        ColumnLayoutHelper
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setMargin(0, 30, 0, 30);
        ChannelHelperAdapter channelHelperAdapter = new ChannelHelperAdapter(result.getData().getChannel(), columnLayoutHelper);
        adapters.addAdapter(channelHelperAdapter);
        channelHelperAdapter.setOnItemClickListener(new ChannelHelperAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(context, result.getData().getChannel().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //添加网格
        GridLayoutHelper gridHelper = new GridLayoutHelper(5);
        gridHelper.setMarginTop(35);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridHelper.setVGap(5);
        //设置水平方向条目的间隔
        gridHelper.setHGap(2);
        gridHelper.setMarginLeft(28);
        gridHelper.setMarginBottom(8);
        //自动填充满布局
        gridHelper.setAutoExpand(true);
        adapters.addAdapter(new ChannelHelperAdapter(result.getData().getChannel(), gridHelper));

        //添加banner
        SingleLayoutHelper singHelper1 = new SingleLayoutHelper();
        singHelper.setBgColor(R.color.colorPrimary);
        singHelper.setMargin(0, 0, 0, 0);
        adapters.addAdapter(new SingleLayoutAdapter(singHelper1));

        rv.setAdapter(adapters);
    }
}
