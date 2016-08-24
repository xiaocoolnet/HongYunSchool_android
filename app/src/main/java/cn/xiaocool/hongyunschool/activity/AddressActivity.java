package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lzy.widget.tab.PagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.fragment.ParentFragment;
import cn.xiaocool.hongyunschool.fragment.TeacherFragment;
import cn.xiaocool.hongyunschool.utils.BaseActivity;

public class AddressActivity extends BaseActivity {
    @BindView(R.id.activity_address_tab)
    PagerSlidingTabStrip activityAddressTab;
    @BindView(R.id.activity_address_viewPager)
    ViewPager activityAddressViewPager;
    private Context context;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        setTopName("通讯录");
        setFragment();
    }

    /**
     * 设置fragment
     */
    private void setFragment() {
        fragments = new ArrayList<>();
        TeacherFragment teacherFragment = new TeacherFragment();
        ParentFragment parentFragment = new ParentFragment();
        fragments.add(teacherFragment);
        fragments.add(parentFragment);
        activityAddressViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        activityAddressTab.setViewPager(activityAddressViewPager);
    }

    @Override
    public void requsetData() {

    }

    /**
     * viewpager适配器
     */
    private class MyAdapter extends FragmentPagerAdapter {

        private String[] titles = {"老师", "家长"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
