package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.ToastUtil;

public class ServiceBuyActivity extends BaseActivity {

    @BindView(R.id.activity_service_buy_cb)
    CheckBox activityServiceBuyCb;
    @BindView(R.id.activity_service_buy_btn)
    TextView activityServiceBuyBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_buy);
        ButterKnife.bind(this);
        setTopName("服务购买");
        context = this;
    }

    @Override
    public void requsetData() {

    }

    @OnClick(R.id.activity_service_buy_btn)
    public void onClick() {
        //立即支付
        if(!activityServiceBuyCb.isChecked()){
            ToastUtil.showShort(context,"请选择支付方式！");
        }else{

        }
    }
}
