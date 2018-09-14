package com.ydy.recyclerview.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.ydy.recyclerview.adapter.FlexBoxLabelAdapter;
import com.ydy.recyclerview.manager.AppManager;
import com.ydy.recyclerview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ydy
 * FlexBoxLayout配合RecyclerView使用
 */
public class FlexBoxLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvWrong;
    private ImageView mIvRight;
    private RecyclerView mRecyclerView;

    private Context context;
    private String[] mAreas = {"美国", "日本", "香港", "韩国", "内地", "台湾犯罪", "欧洲", "印度", "巴西", "澳大利亚文艺", "爱尔兰浪漫喜剧", "比利时", "墨西哥", "波兰", "土耳其"};
    private String[] mTypes = {"日本爱情", "美国科幻", "青春", "喜剧", "动作", "犯罪", "纪录片", "黑色幽默", "动画", "悬疑", "文艺", "恐怖", "战争", "家庭", "浪漫", "励志", "剧情"};
    private FlexBoxLabelAdapter labelAdapter;
    private List<String> selectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout);
        AppManager.getAppManager().addActivity(this);
        context = this;
        getIntentData();
        initView();
        setListener();
        initAdapter();
    }

    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectList = (List<String>) extras.get("selected");
        }
    }

    private void setListener() {
        mIvWrong.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
    }

    private void initView() {
        mIvWrong = (ImageView) findViewById(R.id.iv_wrong);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initAdapter() {
        List<String> dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(mAreas));
        dataList.addAll(Arrays.asList(mTypes));
        labelAdapter = new FlexBoxLabelAdapter(context, dataList, selectList);
        FlexboxLayoutManager manager = new FlexboxLayoutManager();
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(labelAdapter);
        //点击事件
//        labelAdapter.setOnItemClickListener(new FlexBoxLabelAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                FlexBoxLabelAdapter.ViewHolder holder = (FlexBoxLabelAdapter.ViewHolder) view.getTag();
//                int color = holder.mTag.getCurrentTextColor();
//                if(color == context.getResources().getColor(R.color.colorAccent)){
//                    holder.mTag.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                    holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorPrimary));
//                    String text = holder.mTag.getText().toString();
//                    selectList.remove(text);
//                }else{
//                    holder.mTag.setTextColor(context.getResources().getColor(R.color.colorAccent));
//                    holder.mTag.setStrokeColor(context.getResources().getColor(R.color.colorAccent));
//                    String text = holder.mTag.getText().toString();
//                    selectList.add(text);
//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wrong:
                finish();
                break;
            case R.id.iv_right:
                List<String> selectList = labelAdapter.getSelectList();
                Toast.makeText(context, selectList.toString(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
