package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;

import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;

public class MyIntegrationActivity extends BaseActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integration);
        setTopName("我的积分");
        context = this;
    }

    @Override
    public void requsetData() {

    }
}
