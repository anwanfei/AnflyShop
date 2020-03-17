package com.anfly.anflyshop.ui.sort;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.sort.GoodsShopInfoConstract;
import com.anfly.anflyshop.model.bean.CardAddBean;
import com.anfly.anflyshop.model.bean.GoodsShopDetailBean;
import com.anfly.anflyshop.presenter.sort.GoodsShopInfoPresenter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private int numShop = 1;
    private PopupWindow pop;
    private int goods_id;
    private int productId;

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
        GoodsShopDetailBean.DataBeanX.ProductListBean productListBean = data.getProductList().get(0);
        goods_id = productListBean.getGoods_id();
        productId = productListBean.getId();

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
        tvPrice.setText("¥" + String.valueOf(info.getRetail_price()));
        String goods_desc = info.getGoods_desc();
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>" + css_str + "</style></head><body>");
        sb.append(goods_desc + "</body></html>");
        webviewGoodsInfo.loadData(sb.toString(), "text/html", "utf-8");

    }

    @Override
    public void getCartAddResponse(CardAddBean cardAddBean) {
    }

    @OnClick({R.id.iv_star, R.id.iv_cart, R.id.tv_buy_now, R.id.tv_add_shop_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_star:
                break;
            case R.id.iv_cart:
                break;
            case R.id.tv_buy_now:
                break;
            case R.id.tv_add_shop_cart:
                showShopPop();
                break;
        }
    }

    private void showShopPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_shop_cart, null);
        TextView tv_reduce = view.findViewById(R.id.tv_reduce);
        TextView tv_num_shop = view.findViewById(R.id.tv_num_shop);
        TextView tv_add = view.findViewById(R.id.tv_add);
        TextView tv_choosed = view.findViewById(R.id.tv_choosed);
        TextView tv_price = view.findViewById(R.id.tv_price);
        ImageView iv_close_pop = view.findViewById(R.id.iv_close_pop);
        ImageView iv_add_shop_cart = view.findViewById(R.id.iv_add_shop_cart);


        if (pop == null) {
            pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        iv_close_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numShop++;
                tv_num_shop.setText(String.valueOf(numShop));
            }
        });
        tv_reduce.setOnClickListener((v) -> {
            if (numShop > 1) {
                numShop--;
                tv_num_shop.setText(String.valueOf(numShop));
            } else {
                Toast.makeText(GoodsShopInfoActivity.this, "购买数量不能为负数", Toast.LENGTH_SHORT).show();
            }

        });

        if (pop.isShowing()) {
            presener.getCardAddData(goods_id, productId, numShop);
            pop.dismiss();
        } else {
            pop.showAtLocation(clBottom, Gravity.BOTTOM, 0, clBottom.getHeight());
        }

//        setBackGroundAlpha(0.5f);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1f);
            }
        });
    }

    private void setBackGroundAlpha(float alpha) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alpha;
        window.setAttributes(attributes);
    }

}
