package com.anfly.anflyshop.ui.home;

import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.presenter.home.NewHomePresenter;
import com.anfly.anflyshop.ui.home.adapter.NewHomeAdapter;
import com.anfly.anflyshop.utils.GlideImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewHomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {
    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<HomeBean.HomeListBean> list;
    private NewHomeAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected NewHomePresenter createPresenter() {
        return new NewHomePresenter();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        adapter = new NewHomeAdapter(list);
        rv.setAdapter(adapter);

        //计算当前条目所占条目的比重
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int itemType = list.get(i).getItemType();
                switch (itemType) {
                    case HomeBean.HomeListBean.TYPE_BANNER:
                    case HomeBean.HomeListBean.TYPE_CHANNEL:
                    case HomeBean.HomeListBean.TYPE_TITLE:
                    case HomeBean.HomeListBean.TYPE_HOTGOOD:
                    case HomeBean.HomeListBean.TYPE_TOPIC:
                    case HomeBean.HomeListBean.TYPE_VIEW_LINE:
                        return 2;
                    case HomeBean.HomeListBean.TYPE_BRAND:
                    case HomeBean.HomeListBean.TYPE_NEWGOOD:
                    case HomeBean.HomeListBean.TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        adapter.bindToRecyclerView(rv);
    }

    @Override
    protected void initData() {
        presener.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {

    }

    @Override
    public void getHomeDataResponse(List<HomeBean.HomeListBean> data) {
        addHeader(data.remove(0));
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void addHeader(HomeBean.HomeListBean remove) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_layout, null);
        Banner banner = view.findViewById(R.id.banner);
        List<String> imgs = new ArrayList<>();
        for (HomeBean.DataBean.BannerBean item : (List<HomeBean.DataBean.BannerBean>) remove.data) {
            imgs.add(item.getImage_url());
        }
        banner.tag = "true";
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgs);
        banner.start();
        adapter.addHeaderView(view);
    }
}
