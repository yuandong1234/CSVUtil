package com.yuong.csv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yuong.csv.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                generate();
                break;
        }

    }

    private void initView() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    private void generate() {
        Object[] head = {"姓名", "性别", "日期"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < 100; i++) {
            rowList = new ArrayList<Object>();
            rowList.add("test " + i);
            rowList.add(i % 2 == 0 ? "男" : "女");
            rowList.add(DateUtil.getNowTime(DateUtil.DATE_YMD));
            dataList.add(rowList);
        }

        long time = System.currentTimeMillis();
        CSVFactory factory = new CSVFactory.Builder().
                fileName(time + ".csv").
                headTitleList(headList).
                dataList(dataList).
                Build();
        factory.create();

    }
}
