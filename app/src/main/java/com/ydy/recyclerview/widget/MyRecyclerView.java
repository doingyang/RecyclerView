package com.ydy.recyclerview.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gavin.com.library.BaseDecoration;

public class MyRecyclerView extends RecyclerView {

    private BaseDecoration mDecoration;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addItemDecoration(@NonNull ItemDecoration decor) {
        if (decor != null && decor instanceof BaseDecoration) {
            mDecoration = (BaseDecoration) decor;
        }
        super.addItemDecoration(decor);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mDecoration != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDecoration.onEventDown(event);
                    break;
                case MotionEvent.ACTION_UP:
                    if (mDecoration.onEventUp(event)) {
                        return true;
                    }
                    break;
                default:
            }
        }
        return super.onInterceptTouchEvent(event);
    }
}

