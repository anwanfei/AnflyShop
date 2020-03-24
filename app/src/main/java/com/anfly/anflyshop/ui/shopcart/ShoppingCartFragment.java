package com.anfly.anflyshop.ui.shopcart;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseAdapter;
import com.anfly.anflyshop.base.BaseFragment;
import com.anfly.anflyshop.interfaces.shopcart.ShopCartConstract;
import com.anfly.anflyshop.model.bean.CartIndexBean;
import com.anfly.anflyshop.presenter.shopcart.ShopCartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends BaseFragment<ShopCartConstract.Presenter> implements ShopCartConstract.View {


    @BindView(R.id.rv_shop_cart)
    RecyclerView rvShopCart;
    @BindView(R.id.cb_choose_all)
    CheckBox cbChooseAll;
    @BindView(R.id.tv_price_all)
    TextView tvPriceAll;
    @BindView(R.id.tv_edit_complete)
    TextView tvEditComplete;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.cl_bottom)
    ConstraintLayout clBottom;
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getItemState(String position) {
//        int price = 0;
//        int number = 0;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).isChecked()) {
//                price += list.get(i).getMarket_price();
//                number++;
//            }
//        }
//        if (number == list.size()) {
//            cbChooseAll.setChecked(true);
//        } else {
//            cbChooseAll.setChecked(false);
//        }
//        tvPriceAll.setText("¥" + price);
//        cbChooseAll.setText("全选(" + number + ")");
    }

    @Override
    protected ShopCartConstract.Presenter createPresenter() {
        return new ShopCartPresenter();
    }

    @Override
    protected void initView() {
        rvShopCart.setLayoutManager(new LinearLayoutManager(context));
        rvShopCart.addItemDecoration(new DividerItemDecoration(context, RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new ShopCartAdapter(list, context);
        rvShopCart.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
        super.initListener();
        cbChooseAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int price = 0;
                //遍历整个集合，如果全选CheckBox选中，给集合中每一个条目的CheckBox置为选中
                for (CartIndexBean.DataBean.CartListBean cartListBean : list) {
                    if (isChecked) {
                        cartListBean.setChecked(true);
                        int retail_price = cartListBean.getRetail_price();
                        price += retail_price;
                        cbChooseAll.setText("全选(" + list.size() + ")");
                        tvPriceAll.setText("¥" + price);
                    } else {
                        cartListBean.setChecked(false);
                        cbChooseAll.setText("全选(0)");
                        tvPriceAll.setText("¥0");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnItemClickLIstener(new BaseAdapter.OnItemClickLIstener() {
            @Override
            public void onItemClick(BaseAdapter.BaseViewHolder holder, int position) {
                int number = 0;
                int price = 0;
                CartIndexBean.DataBean.CartListBean cartListBean = list.get(position);
                boolean checked = cartListBean.isChecked();
                if (checked) {
                    cartListBean.setChecked(false);
                } else {
                    cartListBean.setChecked(true);
                }
                adapter.notifyDataSetChanged();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        number++;
                        price += list.get(i).getRetail_price();
                    }
                }
                if (number == list.size()) {
                    cbChooseAll.setChecked(true);
                }
                if (number == 0) {
                    cbChooseAll.setChecked(false);
                }

                cbChooseAll.setText("全选(" + number + ")");
                tvPriceAll.setText("¥" + price);
            }
        });
    }

    @Override
    protected void initData() {
        presener.getCartIndexData();
    }


    @Override
    public void getCartIndexResponse(CartIndexBean cartIndexBean) {
        int price = 0;
        List<CartIndexBean.DataBean.CartListBean> cartList = cartIndexBean.getData().getCartList();
        list.addAll(cartList);

        if (list.size() > 0) {
            for (CartIndexBean.DataBean.CartListBean cartListBean : list) {
                cartListBean.setChecked(true);
                int retail_price = cartListBean.getRetail_price();
                price += retail_price;
            }

            cbChooseAll.setText("全选(" + list.size() + ")");
            tvPriceAll.setText("¥" + price);

            cbChooseAll.setChecked(true);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_edit_complete, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_complete:

                String state = tvEditComplete.getText().toString();
                if ("编辑".equals(state)) {// 编辑状态    显示的name   隐藏 添加数量

                    tvBuy.setText("删除所有");
                    tvEditComplete.setText("完成");
                } else {// 完成    显示的 添加商品的数量

                    tvBuy.setText("下单");
                    tvEditComplete.setText("编辑");
                }
                adapter.setItemVisibility(tvEditComplete.getText().toString(), true);
                break;
            case R.id.tv_buy:
                if (list == null || list.size() <= 0) {
                    Toast.makeText(getActivity(), "请先选中商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", list);
                goToActivity(ConfirmOrderActivity.class, bundle);
                break;
        }
    }
}
