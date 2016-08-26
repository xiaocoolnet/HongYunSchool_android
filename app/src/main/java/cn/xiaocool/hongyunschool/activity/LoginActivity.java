package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.view.CleanableEditText;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.activity_login_ed_phone)
    CleanableEditText activityLoginEdPhone;
    @BindView(R.id.activity_login_ed_psw)
    CleanableEditText activityLoginEdPsw;
    @BindView(R.id.activity_login_btn_login)
    TextView activityLoginBtnLogin;
    @BindView(R.id.activity_login_btn_forgetpsw)
    TextView activityLoginBtnForgetpsw;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTopName("泓云校");
        context = this;
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_login_btn_login, R.id.activity_login_btn_forgetpsw})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.activity_login_btn_login:
                break;
            //忘记密码
            case R.id.activity_login_btn_forgetpsw:
                startActivity(ForgetPswActivity.class);
                break;
        }
    }
}
