package com.ydy.recyclerview.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.ydy.recyclerview.manager.AppManager;
import com.ydy.recyclerview.R;
import com.ydy.recyclerview.bean.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydy
 * FlexboxLayout在布局中的独立用法
 */
public class FlexBoxNormalActivity extends AppCompatActivity {

    private FlexboxLayout mFlexBoxLayout;
    private List<Label> labelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_normal);
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        mFlexBoxLayout = (FlexboxLayout) findViewById(R.id.flex_box_layout);
    }

    private void initData() {
        Label tags1 = new Label("文asdfasdfasdf字", false);
        Label tags2 = new Label("文s字", false);
        Label tags3 = new Label("文dds字", false);
        Label tags4 = new Label("文fsadfa字", false);
        Label tags5 = new Label("文sdfafggg字", false);
        Label tags6 = new Label("文s字", false);
        Label tags7 = new Label("文gas字", false);
        Label tags8 = new Label("文dsdgfasgggg字", false);
        Label tags9 = new Label("文asdgasdg字", false);
        labelList.add(tags1);
        labelList.add(tags2);
        labelList.add(tags3);
        labelList.add(tags4);
        labelList.add(tags5);
        labelList.add(tags6);
        labelList.add(tags7);
        labelList.add(tags8);
        labelList.add(tags9);
        showLabels(labelList);
    }

    private void showLabels(List<Label> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            TextView textView = createTextView(dataList.get(i).getText());
            mFlexBoxLayout.addView(textView);
            //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
//                layoutParams.setFlexBasisPercent(0.5f);
//                layoutParams.flexBasisPercent = 0.3f;
                layoutParams.setFlexGrow(0.5f);
            }
        }
        //点击事件
        for (int i = 0; i < mFlexBoxLayout.getChildCount(); i++) {
            //获取每一个childView（添加的TextView）
            final TextView childView = (TextView) mFlexBoxLayout.getChildAt(i);
            //点击事件
            mFlexBoxLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FlexBoxNormalActivity.this, childView.getText().toString() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        //字体颜色
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        //设置字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(30, 30, 30, 30);
        //背景
        textView.setBackgroundResource(R.color.gray);
        return textView;
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
