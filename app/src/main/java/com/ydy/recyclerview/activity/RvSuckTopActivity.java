package com.ydy.recyclerview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydy.recyclerview.R;
import com.ydy.recyclerview.adapter.RvSuckTopAdapter;
import com.ydy.recyclerview.bean.City;
import com.ydy.recyclerview.bean.News;
import com.ydy.recyclerview.constant.Constants;
import com.ydy.recyclerview.util.CityUtil;
import com.ydy.recyclerview.util.DensityUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RvSuckTopActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<News> mNewsList = new ArrayList<>();
    private List<City> mCityList = new ArrayList<>();
    private RvSuckTopAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_suck_top);
        initView();
        initData();
//        initAdapter();
        initAdapter2();
    }

    private void initAdapter2() {
        PowerGroupListener groupListener = new PowerGroupListener() {
            @Override
            public String getGroupName(int position) {
                if (mCityList.size() > position && position > -1) {
                    //获取分组名
                    return mCityList.get(position).getProvince();
                }
                return null;
            }

            @Override
            public View getGroupView(int position) {
                //获取自定义的组View
                if (mCityList.size() > position) {
                    View view = getLayoutInflater().inflate(R.layout.item_group, null, false);
                    //处理自定义的组View
                    ((TextView) view.findViewById(R.id.tv)).setText(mCityList.get(position).getProvince());
                    ((ImageView) view.findViewById(R.id.iv)).setImageResource(mCityList.get(position).getIcon());
                    if (position == 0) {
                        ((ImageView) view.findViewById(R.id.iv_2)).setVisibility(View.VISIBLE);
                    } else {
                        ((ImageView) view.findViewById(R.id.iv_2)).setVisibility(View.GONE);
                    }
                    return view;
                } else {
                    return null;
                }
            }
        };
        PowerfulStickyDecoration decoration = PowerfulStickyDecoration.Builder
                .init(groupListener)
                .setGroupHeight(DensityUtil.dip2px(this, 40))     //设置高度
                .setGroupBackground(Color.parseColor("#FF0080"))        //设置背景
                .setDivideColor(Color.parseColor("#CCCCCC"))            //分割线颜色
                .setDivideHeight(DensityUtil.dip2px(this, 1))     //分割线高度
                .setCacheEnable(true)                                              //是否使用缓存
//                .setHeaderCount(3)
                .setOnClickListener(new OnGroupClickListener() {//点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position, int id) {//Group点击事件
                        String content = "onGroupClick --> " + mCityList.get(position).getProvince() + " --> " + position;
                        Toast.makeText(RvSuckTopActivity.this, content, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        //------------------------------------------------------------------------------------------

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(manager);
        //需要在setLayoutManager()之后调用addItemDecoration()
        mRv.addItemDecoration(decoration);
        mAdapter = new RvSuckTopAdapter(this, mCityList);
        mRv.setAdapter(mAdapter);
    }

    private void initAdapter() {
        GroupListener groupListener = new GroupListener() {
            @Override
            public String getGroupName(int position) {
                if (mCityList.size() > position && position > -1) {
                    //获取分组名
                    return mCityList.get(position).getProvince();
                }
                return null;
            }
        };
        StickyDecoration decoration = StickyDecoration.Builder
                .init(groupListener)
                //.resetSpan(mRv, (GridLayoutManager) manager)//重置span（使用GridLayoutManager时必须调用）
                .setGroupBackground(Color.parseColor("#FF0080"))//分组背景色
                .setGroupHeight(DensityUtil.dip2px(this, 35))//分组高度
                .setGroupTextColor(Color.BLACK)//字体颜色 （默认）
                .setGroupTextSize(DensityUtil.sp2px(this, 16))//字体大小
                .setTextSideMargin(DensityUtil.dip2px(this, 10))//边距   靠左时为左边距  靠右时为右边距
                .setDivideColor(Color.parseColor("#CCCCCC"))//分割线颜色
                .setDivideHeight(DensityUtil.dip2px(this, 2))//分割线高度 (默认没有分割线)
                //.setHeaderCount(2)//header数量（默认0）
                .setOnClickListener(new OnGroupClickListener() {//点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position, int id) {//Group点击事件
                        String content = "onGroupClick --> " + mCityList.get(position).getProvince() + " --> " + position;
                        Toast.makeText(RvSuckTopActivity.this, content, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        //------------------------------------------------------------------------------------------

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(manager);
        //需要在setLayoutManager()之后调用addItemDecoration()
        mRv.addItemDecoration(decoration);
        mAdapter = new RvSuckTopAdapter(this, mCityList);
        mRv.setAdapter(mAdapter);
    }

//    public void onRefresh(View v) {
//        dataList.clear();
//        dataList.addAll(CityUtil.getRandomCityList());
//        mAdapter.notifyDataSetChanged();
//        decoration.clearCache();
//    }

    private void initData() {
        Type typeToken = new TypeToken<List<News>>() {
        }.getType();
        List<News> newsList = new Gson().fromJson(Constants.STR_NEWS, typeToken);
        mNewsList.clear();
        mNewsList.addAll(newsList);

        mCityList.addAll(CityUtil.getCityList());
    }

    private void initView() {
        mRv = findViewById(R.id.rv_suck_top);
    }
}
