package com.ydy.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ydy.recyclerview.R;
import com.ydy.recyclerview.bean.City;
import com.ydy.recyclerview.bean.News;

import java.util.List;

/**
 * **************************************************
 * 文件名称: RvSuckTopAdapter
 * 创建作者: Created by ydy
 * 创建时间: 2019/3/18 17:19
 * 文件描述:
 * 注意事项:
 * 修改历史: 2019/3/18 初始版本
 * **************************************************
 */
public class RvSuckTopAdapter extends RecyclerView.Adapter<RvSuckTopAdapter.MyViewHolder> {

    private Context context;
    private List<City> dataList;
    private LayoutInflater mInflater;

    public RvSuckTopAdapter(Context context, List<City> dataList) {
        this.context = context;
        this.dataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        String data = dataList.get(position).getName();
        holder.tv.setText(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item click " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
