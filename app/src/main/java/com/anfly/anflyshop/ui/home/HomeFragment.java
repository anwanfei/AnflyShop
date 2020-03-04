package com.anfly.anflyshop.ui.home;


import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.home.HomeConstract;
import com.anfly.anflyshop.model.bean.HomeBean;
import com.anfly.anflyshop.presenter.home.HomePresenter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    @BindView(R.id.banner)
    Banner banner;

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

        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        for (int i = 0; i < banners.size(); i++) {
            images.add(banners.get(i).getImage_url());
            titles.add(banners.get(i).getName());
        }
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE).setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setBannerTitles(titles).start();


    }
}
