package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;

public class ChangePswActivity extends BaseActivity {

    @BindView(R.id.activity_change_psw_ed_oldpsw)
    EditText activityChangePswEdOldpsw;
    @BindView(R.id.activity_change_psw_ed_newpsw)
    EditText activityChangePswEdNewpsw;
    @BindView(R.id.activity_change_psw_ed_confirmpsw)
    EditText activityChangePswEdConfirmpsw;
    @BindView(R.id.activity_change_psw_tv_submit)
    TextView activityChangePswTvSubmit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psw);
        ButterKnife.bind(this);
        setTopName("密码修改");
        context = this;
    }

    @Override
    public void requsetData() {

    }

    @OnClick(R.id.activity_change_psw_tv_submit)
    public void onClick() {
    }
}
