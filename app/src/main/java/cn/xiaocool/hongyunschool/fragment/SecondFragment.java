package cn.xiaocool.hongyunschool.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.activity.ClassNewsActivity;
import cn.xiaocool.hongyunschool.activity.MessageActivity;
import cn.xiaocool.hongyunschool.activity.SchoolAnnounceActivity;
import cn.xiaocool.hongyunschool.activity.SchoolNewsActivity;
import cn.xiaocool.hongyunschool.utils.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment {


    @BindView(R.id.second_top_name)
    TextView secondTopName;
    @BindView(R.id.sn_tv_mirror_title)
    TextView snTvMirrorTitle;
    @BindView(R.id.cn_tv_mirror_title)
    TextView cnTvMirrorTitle;
    @BindView(R.id.sa_tv_mirror_title)
    TextView saTvMirrorTitle;
    @BindView(R.id.msge_tv_mirror_title)
    TextView msgeTvMirrorTitle;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    @Override
    public void initData() {
        secondTopName.setText("消息");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.second_rl_school_news, R.id.second_rl_class_news, R.id.second_rl_school_announce, R.id.second_rl_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.second_rl_school_news:
                startActivity(new Intent(mActivity, SchoolNewsActivity.class));
                break;
            case R.id.second_rl_class_news:
                startActivity(new Intent(mActivity, ClassNewsActivity.class));
                break;
            case R.id.second_rl_school_announce:
                startActivity(new Intent(mActivity, SchoolAnnounceActivity.class));
                break;
            case R.id.second_rl_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
        }
    }
}
