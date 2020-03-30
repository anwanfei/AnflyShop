package com.anfly.anflyshop.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 封装内容
 * 1.数据公用 -- 用泛型
 * 2.创建item条目
 * 3.封装BaseViewHolder，实现动态获取item中的组件（用空间换时间）
 * 4.封装数据绑定到界面
 * 5.数据刷新和加载更多
 *
 * @param <T>:数据类型
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    protected List<T> data;
    protected Context mContext;
    private OnItemClickLIstener onItemClickLIstener;
    //用来响应列表条目中的触发事件
    protected View.OnClickListener onClickListener;

    public BaseAdapter(List<T> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    public void addOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 创建条目布局，并关联到viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayout(), parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLIstener != null) {
                    onItemClickLIstener.onItemClick(baseViewHolder, baseViewHolder.getLayoutPosition());
                }
            }
        });
        return baseViewHolder;
    }

    /**
     * 定义抽象方法，获取条目布局
     *
     * @return
     */
    public abstract int getLayout();

    /**
     * 定义个绑定数据到item的方法
     *
     * @param holder
     * @param data
     */
    public abstract void bindData(BaseViewHolder holder, T data);

    /**
     * 绑定数据到条目
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        T t = data.get(position);
        bindData(baseViewHolder, t);
    }

    /**
     * 获取条目数据
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 全局更新数据
     * 传一个新的数据集合过来替换原来的数据
     *
     * @param list
     */
    public void updataList(List<T> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void updataMoreList(List<T> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * viewholder封装类
     * <p>
     * 使用空间换时间，即消耗内存
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        SparseArray views;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray();
        }

        /**
         * 获取item对应的vieww
         * 为了减少findviewbyId，减少时间
         *
         * @param id
         * @return
         */
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
        void onItemClick(BaseViewHolder holder, int position);
    }
}
