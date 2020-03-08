package com.anfly.anflyshop.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

public abstract class BaseDelegateAdapter<T> extends DelegateAdapter.Adapter {
    protected Context mContext;
    protected List<T> data;
    protected LayoutHelper layoutHelper;
    private OnItemClickLIstener onItemClickLIstener;

    public BaseDelegateAdapter(Context mContext, List<T> data, LayoutHelper layoutHelper) {
        this.mContext = mContext;
        this.data = data;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        BaseDelegateViewHolder baseDelegateViewHolder = new BaseDelegateViewHolder(view);
        return baseDelegateViewHolder;
    }

    public abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseDelegateViewHolder baseDelegateViewHolder = (BaseDelegateViewHolder) holder;
        T t = data.get(position);
        bindData(baseDelegateViewHolder, t);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLIstener != null) {
                    onItemClickLIstener.onItemClick(baseDelegateViewHolder, position);
                }
            }
        });
    }

    protected abstract void bindData(BaseDelegateViewHolder holder, T data);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class BaseDelegateViewHolder extends RecyclerView.ViewHolder {

        SparseArray views;

        public BaseDelegateViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray();
        }

        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }

    public void setOnItemClickLIstener(OnItemClickLIstener onItemClickLIstener) {
        this.onItemClickLIstener = onItemClickLIstener;
    }

    public interface OnItemClickLIstener {
        void onItemClick(BaseDelegateViewHolder holder, int position);
    }
}
