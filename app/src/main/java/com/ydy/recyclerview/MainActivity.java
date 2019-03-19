package com.ydy.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ydy.recyclerview.activity.RvRefreshMoreActivity;
import com.ydy.recyclerview.activity.RvBasicUseActivity;
import com.ydy.recyclerview.activity.FlexBoxLayoutActivity;
import com.ydy.recyclerview.activity.FlexBoxNormalActivity;
import com.ydy.recyclerview.activity.RvSuckTopActivity;
import com.ydy.recyclerview.manager.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydy
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnBasic;
    private Button mBtnHF;
    private Button mBtnFlexBox;
    private Button mBtnFlexBoxHigh;
    private Button mBtnRvXiDin;

    private List<String> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        initView();
        setListener();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_basic:
                intent.setClass(this, RvBasicUseActivity.class);
                break;
            case R.id.btn_hf:
                intent.setClass(this, RvRefreshMoreActivity.class);
                break;
            case R.id.btn_flex_box:
                intent.setClass(this, FlexBoxNormalActivity.class);
                break;
            case R.id.btn_flex_box_high:
                selected.add("美国科幻");
                selected.add("纪录片");
                selected.add("澳大利亚文艺");
                selected.add("战争");
                intent.setClass(this, FlexBoxLayoutActivity.class);
                intent.putStringArrayListExtra("selected", (ArrayList<String>) selected);
                break;
            case R.id.btn_rv_xidin:
                intent.setClass(this, RvSuckTopActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    private void setListener() {
        mBtnBasic.setOnClickListener(this);
        mBtnHF.setOnClickListener(this);
        mBtnFlexBox.setOnClickListener(this);
        mBtnFlexBoxHigh.setOnClickListener(this);
        mBtnRvXiDin.setOnClickListener(this);
    }

    private void initView() {
        mBtnBasic = (Button) findViewById(R.id.btn_basic);
        mBtnHF = (Button) findViewById(R.id.btn_hf);
        mBtnFlexBox = (Button) findViewById(R.id.btn_flex_box);
        mBtnFlexBoxHigh = (Button) findViewById(R.id.btn_flex_box_high);
        mBtnRvXiDin = (Button) findViewById(R.id.btn_rv_xidin);
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
