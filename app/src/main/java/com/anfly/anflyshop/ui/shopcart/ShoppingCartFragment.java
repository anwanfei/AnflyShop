package com.anfly.anflyshop.ui.shopcart;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.shopcart.ShopCartConstract;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.anfly.anflyshop.presenter.shopcart.ShopCartPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends BaseFragment<ShopCartConstract.Presenter> implements ShopCartConstract.View {


    @BindView(R.id.rv_shop_cart)
    RecyclerView rvShopCart;
    private ArrayList<CartIndexBean.DataBean.CartListBean> list;
    private ShopCartAdapter adapter;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected ShopCartConstract.Presenter createPresenter() {
        return new ShopCartPresenter();
    }

    @Override
    protected void initView() {
        rvShopCart.setLayoutManager(new LinearLayoutManager(context));
        rvShopCart.addItemDecoration(new DividerItemDecoration(context,RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new ShopCartAdapter(list, context);
        rvShopCart.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presener.getCartIndexData();
    }


    @Override
    public void getCartIndexResponse(CartIndexBean cartIndexBean) {
        List<CartIndexBean.DataBean.CartListBean> cartList = cartIndexBean.getData().getCartList();
        list.addAll(cartList);
        adapter.notifyDataSetChanged();
    }
}
