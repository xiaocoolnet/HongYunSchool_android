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
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.activity_personal_info_iv_avatar)
    CircleImageView activityPersonalInfoIvAvatar;
    @BindView(R.id.activity_personal_info_rl_avatar)
    RelativeLayout activityPersonalInfoRlAvatar;
    @BindView(R.id.activity_personal_info_tv_name)
    TextView activityPersonalInfoTvName;
    @BindView(R.id.activity_personal_info_rl_name)
    RelativeLayout activityPersonalInfoRlName;
    @BindView(R.id.activity_personal_info_tv_sex)
    TextView activityPersonalInfoTvSex;
    @BindView(R.id.activity_personal_info_rl_sex)
    RelativeLayout activityPersonalInfoRlSex;
    @BindView(R.id.activity_personal_info_tv_phone)
    TextView activityPersonalInfoTvPhone;
    @BindView(R.id.activity_personal_info_rl_phone)
    RelativeLayout activityPersonalInfoRlPhone;
    @BindView(R.id.activity_personal_info_rl_changepsw)
    RelativeLayout activityPersonalInfoRlChangepsw;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        context = this;
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_personal_info_rl_avatar, R.id.activity_personal_info_rl_name, R.id.activity_personal_info_rl_sex, R.id.activity_personal_info_rl_changepsw})
    public void onClick(View view) {
        switch (view.getId()) {
            //修改头像
            case R.id.activity_personal_info_rl_avatar:
                break;
            //修改名字
            case R.id.activity_personal_info_rl_name:
                break;
            //修改性别
            case R.id.activity_personal_info_rl_sex:
                break;
            //修改密码
            case R.id.activity_personal_info_rl_changepsw:
                startActivity(ChangePswActivity.class);
                break;
        }
    }
}
