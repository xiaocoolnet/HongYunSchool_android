package cn.xiaocool.hongyunschool.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.fragment.FirstFragment;
import cn.xiaocool.hongyunschool.fragment.FourFragment;
import cn.xiaocool.hongyunschool.fragment.SecondFragment;
import cn.xiaocool.hongyunschool.fragment.SecondParentFragment;
import cn.xiaocool.hongyunschool.fragment.ThirdFragment;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.SPUtils;


public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;
    @BindView(R.id.main_tab_home)
    RadioButton mainTabHome;
    @BindView(R.id.main_tab_sort)
    RadioButton mainTabSort;
    @BindView(R.id.main_tab_quick)
    RadioButton mainTabQuick;
    @BindView(R.id.main_tab_mine)
    RadioButton mainTabMine;
    private int index, currentTabIndex;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourFragment fourFragment;
    private SecondParentFragment secondParentFragment;
    private Fragment[] fragments;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideTopView();
        context = this;
        init();
        Log.e("TAG", SPUtils.get(context, LocalConstant.USER_IS_PRINSIPLE,"").toString()
                + SPUtils.get(context, LocalConstant.USER_IS_CLASSLEADER,"").toString()
                + SPUtils.get(context, LocalConstant.USER_CLASSID,"").toString());
        /*TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
       *//* String DEVICE_ID = tm.getDeviceId();
        Log.e("TAG",DEVICE_ID);*/
    }

    @Override
    public void requsetData() {

    }

    private void init() {
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        fourFragment = new FourFragment();
        secondParentFragment = new SecondParentFragment();
        if(SPUtils.get(context,LocalConstant.USER_TYPE,"1").equals("0")){
            fragments = new Fragment[]{firstFragment,secondParentFragment,thirdFragment,fourFragment};
        }else{
            fragments = new Fragment[]{firstFragment,secondFragment,thirdFragment,fourFragment};
        }
        getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }

    @OnClick({R.id.main_tab_home, R.id.main_tab_sort, R.id.main_tab_quick, R.id.main_tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                index = 0;
                break;
            case R.id.main_tab_sort:
                index = 1;
                break;
            case R.id.main_tab_quick:
                index = 2;
                break;
            case R.id.main_tab_mine:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                transaction.add(R.id.fragment_container, fragments[index]);
            }
            transaction.show(fragments[index]);
            transaction.commit();

        }
        currentTabIndex = index;
    }
}
