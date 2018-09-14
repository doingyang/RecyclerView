package com.ydy.recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ydy.recyclerview.R;
import com.ydy.recyclerview.adapter.RvBasicUseAdapter;
import com.ydy.recyclerview.divider.ItemDecorationDividerGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydy
 */
public class RvBasicUseActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RvBasicUseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //显示风格
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//垂直ListView效果
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));//水平ListView效果
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//垂直ListView效果
        //GridView效果，4列
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));//4行，水平滑动
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));//4列，垂直滑动
        // 设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 添加分割线
//        mRecyclerView.addItemDecoration(new ItemDecorationDividerList(this, ItemDecorationDividerList.VERTICAL_LIST));  //垂直型ListView
//        mRecyclerView.addItemDecoration(new ItemDecorationDividerList(this, ItemDecorationDividerList.HORIZONTAL_LIST));//水平型ListView
        //GridView型的分割线
        mRecyclerView.addItemDecoration(new ItemDecorationDividerGrid(this));
        mAdapter = new RvBasicUseAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RvBasicUseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RvBasicUseActivity.this, position + " click", Toast.LENGTH_SHORT).show();
                mAdapter.addData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RvBasicUseActivity.this, position + " long click", Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    private void initData() {
        for (int i = 'A'; i <= 'Z'; i++) {
            mData.add("" + (char) i);
        }
    }
}
