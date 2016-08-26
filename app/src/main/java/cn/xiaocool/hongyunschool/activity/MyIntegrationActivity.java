package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class MyIntegrationActivity extends BaseActivity {
    @BindView(R.id.activity_my_integration_tv_number)
    TextView activityMyIntegrationTvNumber;
    @BindView(R.id.activity_my_integration_lv)
    ListView activityMyIntegrationLv;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integration);
        ButterKnife.bind(this);
        setTopName("积分");
        context = this;
    }

    @Override
    public void requsetData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item");
        }
        activityMyIntegrationLv.setAdapter(new CommonAdapter(context, list, R.layout.item_integration_info) {
            @Override
            public void convert(ViewHolder holder, Object o) {

            }
        });
    }
}
