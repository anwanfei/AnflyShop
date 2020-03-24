package com.anfly.anflyshop.ui.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.search.SearchResultContract;
import com.anfly.anflyshop.model.bean.SearchResultBean;
import com.anfly.anflyshop.presenter.search.SearchResultPresenter;
import com.anfly.anflyshop.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchResultContract.Presenter> implements SearchResultContract.View {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_search_no_result)
    TextView tvSearchNoResult;
    @BindView(R.id.fl)
    FlowLayout fl;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    private String keyword, sort = "default", order = "desc";

    private int page = 1, size = 100, categoryId = 0;
    private ArrayList<SearchResultBean.DataBeanX.DataBean> list;
    private SearchAdapter adapter;

    private ArrayList<String> historyList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchResultContract.Presenter createPresenter() {
        return new SearchResultPresenter();
    }

    @Override
    protected void initView() {
//        etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                keyword = s.toString();
//                if (!TextUtils.isEmpty(keyword) && !historyList.contains(keyword)) {
//                    historyList.add(keyword);
//                }
//                if (list != null && list.size() > 0) {
//                    list.clear();
//                }
//                initData();
//            }
//
//
//        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    hideKeyboard(etSearch);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    keyword = etSearch.getText().toString();
                    if (list != null && list.size() > 0) {
                        list.clear();
                    }
                    initData();
                    return true;
                }
                return false;
            }
        });

        rv.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();
        adapter = new SearchAdapter(list, this);
        rv.setAdapter(adapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(keyword)) {
            tvHistory.setVisibility(View.GONE);
            fl.setVisibility(View.GONE);
            presener.getSearchResultData(keyword, page, size, sort, order, categoryId);

            //
            if (!historyList.contains(keyword)) {
                historyList.add(keyword);
                TextView label = (TextView) LayoutInflater.from(this).inflate(R.layout.item_fl, null);
                label.setText(keyword);
                label.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etSearch.setText(label.getText());
                        etSearch.setSelection(keyword.length());//将光标移至文字末尾
                    }
                });

                fl.addView(label);
            }
        } else {
            tvHistory.setVisibility(View.VISIBLE);
            fl.setVisibility(View.VISIBLE);
        }
        rv.setVisibility(View.GONE);
        tvSearchNoResult.setVisibility(View.GONE);
    }

    @Override
    public void getSearchResultResponse(SearchResultBean searchResultBean) {
        List<SearchResultBean.DataBeanX.DataBean> data = searchResultBean.getData().getData();
        if (data.size() > 0) {
            rv.setVisibility(View.VISIBLE);
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } else {
            tvSearchNoResult.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}
