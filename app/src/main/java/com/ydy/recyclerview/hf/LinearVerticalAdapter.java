package com.ydy.recyclerview.hf;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ydy.recyclerview.R;
import com.ydy.recyclerview.bean.Picture;
import com.ydy.recyclerview.hfutil.RandomUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class LinearVerticalAdapter extends RecyclerView.Adapter<LinearVerticalAdapter.ViewHolder> {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<Picture> pictureList = new ArrayList<>();

    public LinearVerticalAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    public List<Picture> getPictureList() {
        return pictureList;
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_linear_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(pictureList.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_thumb)
        ImageView imgThumb;

        private Picture picture;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void update(@NonNull Picture picture) {
            this.picture = picture;
            Glide.with(activity).load(picture.getImage()).into(imgThumb);
//            Glide.with(activity).load(picture.getImage()).placeholder(R.drawable.image_placeholder).into(imgThumb);
        }

        @OnClick(R.id.btn_item)
        void onBtnItemClick() {
            int position = pictureList.indexOf(picture);
            int newPosition = Math.abs(RandomUtils.RANDOM.nextInt()) % pictureList.size();
            pictureList.add(newPosition, pictureList.remove(position));
            notifyItemMoved(position, newPosition);
        }

        @OnLongClick(R.id.btn_item)
        boolean onBtnItemLongClick() {
            int position = pictureList.indexOf(picture);
            pictureList.remove(position);
            notifyItemRemoved(position);
            return true;
        }
    }
}
