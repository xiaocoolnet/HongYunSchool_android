package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.app.MyApplication;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.SPUtils;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.activity_setting_rl_help)
    RelativeLayout activitySettingRlHelp;
    @BindView(R.id.activity_setting_rl_feedback)
    RelativeLayout activitySettingRlFeedback;
    @BindView(R.id.activity_setting_aboutUs)
    RelativeLayout activitySettingAboutUs;
    @BindView(R.id.activity_setting_rl_update)
    RelativeLayout activitySettingRlUpdate;
    @BindView(R.id.activity_setting_rl_clean)
    RelativeLayout activitySettingRlClean;
    @BindView(R.id.activity_setting_tv_quit)
    TextView activitySettingTvQuit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        context = this;
        setTopName("设置");
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_setting_rl_help, R.id.activity_setting_rl_feedback, R.id.activity_setting_aboutUs, R.id.activity_setting_rl_update, R.id.activity_setting_rl_clean, R.id.activity_setting_tv_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            //使用帮助
            case R.id.activity_setting_rl_help:
                break;
            //意见反馈
            case R.id.activity_setting_rl_feedback:
                break;
            //关于我们
            case R.id.activity_setting_aboutUs:
                break;
            //版本更新
            case R.id.activity_setting_rl_update:
                break;
            //清除缓存
            case R.id.activity_setting_rl_clean:
                break;
            //退出
            case R.id.activity_setting_tv_quit:
                SPUtils.clear(context);
                startActivity(LoginActivity.class);
                MyApplication.getInstance().onTerminate();
                break;
        }
    }
}
