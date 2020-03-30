package com.anfly.anflyshop.ui.specialtopic;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.base.BaseActivity;
import com.anfly.anflyshop.interfaces.specialtopic.SpecialTopicDetailConstract;
import com.anfly.anflyshop.model.bean.TopicDetailBean;
import com.anfly.anflyshop.presenter.SpecialTopic.SpecialTopicDetailPresenter;

import butterknife.BindView;

public class SpecialTopicActivity extends BaseActivity<SpecialTopicDetailConstract.Presenter> implements SpecialTopicDetailConstract.View {

    @BindView(R.id.webview_topic_detail)
    WebView webviewTopicDetail;

    @Override
    protected int getLayout() {
        return R.layout.activity_special_topic;
    }

    @Override
    protected SpecialTopicDetailPresenter createPresenter() {
        return new SpecialTopicDetailPresenter();
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webviewTopicDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getBundleExtra("data");
        int id = bundle.getInt("id");
        presener.getTopicDetailData(id);
    }

    @Override
    public void getSpecialTopicInfoResponse(TopicDetailBean topicDetailBean) {
        TopicDetailBean.DataBean data = topicDetailBean.getData();
        updateWebView(data);
    }

    //商品介绍描述信息
    private void updateWebView(TopicDetailBean.DataBean data) {

        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>" + css_str + "</style></head><body>");
        sb.append(data.getContent().replace("img src=\"","img src=\"http:") + "</body></html>");
        webviewTopicDetail.loadData(sb.toString(), "text/html", "utf-8");

//        List<String> urlList = new ArrayList<>();
//        String[] arr = data.getContent().split("<p>");
//        String head = "<img src=\"";
//        String foot = ".jpg";
//        for (int i = 0; i < arr.length; i++) {
//            if (TextUtils.isEmpty(arr[i])) continue;
//            int start = arr[i].indexOf(head) + head.length();
//            if (start == -1) continue;
//            int end = arr[i].indexOf(foot) + foot.length();
//            String url = arr[i].substring(start, end);
//            urlList.add(url);
//            Log.i("url", url);
//        }
    }
}
