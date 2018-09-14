package com.ydy.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.ydy.recyclerview.R;
import com.ydy.recyclerview.widget.SuperTextView;

import java.util.List;

/**
 * @author ydy
 */
public class FlexBoxLabelAdapter extends RecyclerView.Adapter<FlexBoxLabelAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    private List<String> labelList;
    private List<String> selectList;
    private OnItemClickListener onItemClickListener;

    public FlexBoxLabelAdapter(Context context, List<String> labelList, List<String> selectList) {
        this.context = context;
        this.labelList = labelList;
        this.selectList = selectList;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return labelList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_favor_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mTag.setText(labelList.get(position));
        ViewGroup.LayoutParams params = holder.mTag.getLayoutParams();
        if (params instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) holder.mTag.getLayoutParams();
            layoutParams.setFlexGrow(1.0f);
        }
        if (selectList.contains(holder.mTag.getText().toString())) {
            holder.mTag.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.mTag.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.mTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, holder.getLayoutPosition());
            }
        });
        holder.mTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = holder.mTag.getCurrentTextColor();
                if (color == context.getResources().getColor(R.color.colorAccent)) {
                    holder.mTag.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorPrimary));
                    String text = holder.mTag.getText().toString();
                    selectList.remove(text);
                } else {
                    holder.mTag.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorAccent));
                    String text = holder.mTag.getText().toString();
                    selectList.add(text);
                }
            }
        });
    }

    public List<String> getSelectList() {
        return selectList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SuperTextView mTag;

        public ViewHolder(View itemView) {
            super(itemView);
            //必要的!!!
            itemView.setTag(this);
            mTag = (SuperTextView) itemView.findViewById(R.id.stv_tag);
        }
    }

}
