package com.anfly.anflyshop.ui.my;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.common.Constants;
import com.anfly.anflyshop.model.bean.OwnerBean;
import com.anfly.anflyshop.ui.Login.LoginActivity;
import com.anfly.anflyshop.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cl_header)
    ConstraintLayout clHeader;
    @BindView(R.id.rv_my)
    RecyclerView rvMy;
    private ArrayList<OwnerBean> list;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        String nickName = (String) SharedPreferencesUtil.getInstance().getParam(Constants.NICKNAME, "大飞");
        tvName.setText(nickName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, inflate);
        initData();
        initView();
        return inflate;
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new OwnerBean("我的订单", R.mipmap.profile_profile_crowdfunding_ic, OrderActivity.class));
        list.add(new OwnerBean("优惠券", R.mipmap.profile_profile_reimburse_ic, CouponActivity.class));
        list.add(new OwnerBean("礼品卡", R.mipmap.profile_profile_reimburse_ic, CouponActivity.class));
        list.add(new OwnerBean("我的收藏", R.mipmap.profile_profile_ic_quhua, CouponActivity.class));
        list.add(new OwnerBean("我的足迹", R.mipmap.profile_profile_address_ic, CouponActivity.class));
        list.add(new OwnerBean("会员福利", R.mipmap.profile_profile_ic_select, CouponActivity.class));
        list.add(new OwnerBean("地址管理", R.mipmap.profile_profile_address_ic, CouponActivity.class));
        list.add(new OwnerBean("账号安全", R.mipmap.profile_profile_cooperation_ic, CouponActivity.class));
        list.add(new OwnerBean("联系客服", R.mipmap.profile_profile_phone_ic, CouponActivity.class));
        list.add(new OwnerBean("帮助中心", R.mipmap.profile_profile_phone_ic, CouponActivity.class));
        list.add(new OwnerBean("意见反馈", R.mipmap.profile_profile_phone_ic, CouponActivity.class));
    }

    private void initView() {
        rvMy.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        OwnerAdapter adapter = new OwnerAdapter(getActivity(), list);
        rvMy.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        rvMy.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.HORIZONTAL));
        rvMy.setAdapter(adapter);
        adapter.setOnItemClickListener(new OwnerAdapter.OnItemClickListener() {
            @Override
            public void onItemclick(int poosotion) {
                Intent intent = new Intent(getActivity(), list.get(poosotion).getaClass());
                startActivity(intent);
            }
        });
    }

    private void openLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.iv_header, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header:
                break;
            case R.id.tv_name:
                openLogin();
                break;
        }
    }
}
