package cn.xiaocool.hongyunschool.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment {


    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.swipLayout)
    SwipeRefreshLayout swipLayout;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    protected void initEvent() {
        super.initEvent();

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
}
