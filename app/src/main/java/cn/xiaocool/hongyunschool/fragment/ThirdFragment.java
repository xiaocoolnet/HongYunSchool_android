package cn.xiaocool.hongyunschool.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends BaseFragment {


    @BindView(R.id.top_name)
    TextView topName;
    @BindView(R.id.trend_list)
    ListView trendList;
    @BindView(R.id.swipLayout)
    SwipeRefreshLayout swipLayout;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        topName.setText("动态");
        swipLayout.setColorSchemeResources(R.color.white);
        swipLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.themeColor));
        swipLayout.setProgressViewOffset(true, 10, 100);
        swipLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
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
