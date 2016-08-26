package cn.xiaocool.hongyunschool.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.activity.AddressActivity;
import cn.xiaocool.hongyunschool.activity.MyIntegrationActivity;
import cn.xiaocool.hongyunschool.activity.PersonalInfoActivity;
import cn.xiaocool.hongyunschool.activity.SettingActivity;
import cn.xiaocool.hongyunschool.activity.SystemNewsActivity;
import cn.xiaocool.hongyunschool.utils.BaseFragment;
import cn.xiaocool.hongyunschool.view.RoundImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends BaseFragment {


    @BindView(R.id.top_name)
    TextView topName;
    @BindView(R.id.fragment_four_iv_setting)
    ImageView fragmentFourIvSetting;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.fragment_four_iv_avatar)
    RoundImageView fragmentFourIvAvatar;
    @BindView(R.id.fragment_four_tv_name)
    TextView fragmentFourTvName;
    @BindView(R.id.fragment_four_rl_service)
    RelativeLayout fragmentFourRlService;
    @BindView(R.id.fragment_four_rl_address)
    RelativeLayout fragmentFourRlAddress;
    @BindView(R.id.fragment_four_rl_jifen)
    RelativeLayout fragmentFourRlJifen;
    @BindView(R.id.fragment_four_rl_system)
    RelativeLayout fragmentFourRlSystem;
    @BindView(R.id.fragment_four_rl_online)
    RelativeLayout fragmentFourRlOnline;
    @BindView(R.id.fragment_four_rl_code)
    RelativeLayout fragmentFourRlCode;
    private Context context;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }


    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @OnClick({R.id.fragment_four_iv_setting, R.id.fragment_four_iv_avatar, R.id.fragment_four_rl_service, R.id.fragment_four_rl_address, R.id.fragment_four_rl_jifen, R.id.fragment_four_rl_system, R.id.fragment_four_rl_online, R.id.fragment_four_rl_code})
    public void onClick(View view) {
        switch (view.getId()) {
            //设置
            case R.id.fragment_four_iv_setting:
                context.startActivity(new Intent(context, SettingActivity.class));
                break;
            //个人信息
            case R.id.fragment_four_iv_avatar:
                context.startActivity(new Intent(context, PersonalInfoActivity.class));
                break;
            //服务购买
            case R.id.fragment_four_rl_service:
                break;
            //通讯录
            case R.id.fragment_four_rl_address:
                context.startActivity(new Intent(context, AddressActivity.class));
                break;
            //我的积分
            case R.id.fragment_four_rl_jifen:
                context.startActivity(new Intent(context, MyIntegrationActivity.class));
                break;
            //系统通知
            case R.id.fragment_four_rl_system:
                context.startActivity(new Intent(context, SystemNewsActivity.class));
                break;
            //在线客服
            case R.id.fragment_four_rl_online:
                break;
            //客户端二维码
            case R.id.fragment_four_rl_code:
                break;
        }
    }
}
