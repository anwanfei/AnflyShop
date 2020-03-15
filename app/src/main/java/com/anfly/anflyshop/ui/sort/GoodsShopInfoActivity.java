package com.anfly.anflyshop.ui.sort;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.sort.GoodsShopInfoConstract;
import com.anfly.anflyshop.model.bean.GoodsShopDetailBean;
import com.anfly.anflyshop.presenter.sort.GoodsShopInfoPresenter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsShopInfoActivity extends BaseActivity<GoodsShopInfoConstract.Presenter> implements GoodsShopInfoConstract.View {

    @BindView(R.id.webview_goods_info)
    WebView webviewGoodsInfo;
    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;
    @BindView(R.id.iv_star)
    ImageView ivStar;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.tv_buy_now)
    TextView tvBuyNow;
    @BindView(R.id.tv_add_shop_cart)
    TextView tvAddShopCart;
    @BindView(R.id.cl_bottom)
    ConstraintLayout clBottom;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_goods_brief)
    TextView tvGoodsBrief;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_attribute)
    TextView tvAttribute;
    @BindView(R.id.tv_attributes)
    TextView tvAttributes;
    @BindView(R.id.banner_good_info)
    Banner bannerGoodInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_shop_info;
    }

    @Override
    protected GoodsShopInfoPresenter createPresenter() {
        return new GoodsShopInfoPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getBundleExtra("data");
        int id = bundle.getInt("id");
        presener.getGoodsShopInfoData(id);
    }

    @Override
    public void getGoodsShopInfoResponse(GoodsShopDetailBean goodsShopDetailBean) {
        GoodsShopDetailBean.DataBeanX data = goodsShopDetailBean.getData();

        ArrayList<String> imges = new ArrayList<>();
        for (GoodsShopDetailBean.DataBeanX.GalleryBean galleryBean : data.getGallery()) {
            imges.add(galleryBean.getImg_url());
        }
        bannerGoodInfo.setImages(imges).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();

        List<GoodsShopDetailBean.DataBeanX.AttributeBean> attribute = data.getAttribute();
        StringBuffer stringBuffer = new StringBuffer();
        for (GoodsShopDetailBean.DataBeanX.AttributeBean attributeBean : attribute) {
            stringBuffer.append(attributeBean.getName() + ":" + attributeBean.getValue() + "\n");
        }
        tvAttributes.setText(stringBuffer.toString());

        GoodsShopDetailBean.DataBeanX.InfoBean info = data.getInfo();
        tvName.setText(info.getName());
        tvGoodsBrief.setText(info.getGoods_brief());
        tvPrice.setText("¥"+String.valueOf(info.getRetail_price()));
        String goods_desc = info.getGoods_desc();
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>" + css_str + "</style></head><body>");
        sb.append(goods_desc + "</body></html>");
        webviewGoodsInfo.loadData(sb.toString(), "text/html", "utf-8");

    }
}
