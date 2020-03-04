package com.anfly.anflyshop.ui.specialtopic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.model.bean.SpecialTopicBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialTopicAdapter extends RecyclerView.Adapter<SpecialTopicAdapter.Viewholder> {
    private Context context;
    private ArrayList<SpecialTopicBean.DataBeanX.DataBean> list;

    public SpecialTopicAdapter(Context context, ArrayList<SpecialTopicBean.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specialtopic, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SpecialTopicBean.DataBeanX.DataBean dataBean = list.get(position);

        Glide.with(context).load(dataBean.getScene_pic_url()).into(holder.ivTopic);
        holder.tvTitleTopic.setText(dataBean.getTitle());
        holder.tvTopicDes.setText(dataBean.getSubtitle());
        holder.tvTopicPrice.setText(String.valueOf(dataBean.getPrice_info()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_topic)
        ImageView ivTopic;
        @BindView(R.id.tv_title_topic)
        TextView tvTitleTopic;
        @BindView(R.id.tv_topic_des)
        TextView tvTopicDes;
        @BindView(R.id.tv_topic_price)
        TextView tvTopicPrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
