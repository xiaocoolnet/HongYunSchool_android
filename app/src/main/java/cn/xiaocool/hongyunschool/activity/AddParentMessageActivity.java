package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;

import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;

public class AddParentMessageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent_message);
        setTopName("反馈");

    }

    @Override
    public void requsetData() {

    }
}