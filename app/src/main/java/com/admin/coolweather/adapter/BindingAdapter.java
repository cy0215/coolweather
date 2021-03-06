package com.admin.coolweather.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.coolweather.BR;
import com.admin.coolweather.Base.recycleview.OnItemClickListener;
import com.admin.coolweather.Base.recycleview.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2017/5/6.
 */

public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingHolder>
{

    List<BindingAdapterItem> items = new ArrayList<>();

    private OnItemClickListener  mOnItemClickListener = null;

    private OnItemLongClickListener mOnItemLongClickListener = null;

    public List<BindingAdapterItem> getItems()
    {
        return items;
    }

    public void setItems(List<BindingAdapterItem> items)
    {
        this.items = items;
    }

    /**
     * @return 返回的是adapter的view
     */
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);

        return new BindingHolder(binding);
    }

    /*
    * 设置item点击事件
    * */
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener)
    {
        this.mOnItemClickListener = OnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener)
    {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /*
    * 数据绑定
    * */
    @Override
    public void onBindViewHolder(final BindingHolder holder, final int position)
    {
        holder.bindData(items.get(position));


        if(mOnItemClickListener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(pos);
                }
            });

        }

        if(mOnItemLongClickListener !=null)
        {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemClick(view,pos);
                    return true ;
                }
            });

        }


    }


    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return items.get(position).getViewType();
    }

    public class BindingHolder extends RecyclerView.ViewHolder
    {

        ViewDataBinding binding;
         /**
         * @param binding   可以看作是这个hodler代表的布局的马甲，getRoot()方法会返回整个holder的最顶层的view
         * */
        public BindingHolder(ViewDataBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bindData(BindingAdapterItem item)
        {

            binding.setVariable(BR.item,item); //RecyclerView绑定所有layouts的“item”Variable。
        }

    }
}
