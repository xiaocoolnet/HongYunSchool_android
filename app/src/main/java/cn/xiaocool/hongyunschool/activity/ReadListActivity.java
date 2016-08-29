package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.SchoolNewsSend;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.ViewHolder;
import cn.xiaocool.hongyunschool.view.NoScrollListView;

public class ReadListActivity extends BaseActivity {

    @BindView(R.id.activity_read_list_tv_yidu)
    TextView activityReadListTvYidu;
    @BindView(R.id.activity_read_list_lv_yidu)
    NoScrollListView activityReadListLvYidu;
    @BindView(R.id.activity_read_list_tv_weidu)
    TextView activityReadListTvWeidu;
    @BindView(R.id.activity_read_list_lv_weidu)
    NoScrollListView activityReadListLvWeidu;
    private Context context;
    private ArrayList<SchoolNewsSend.ReceiverBean> notReads = new ArrayList<>();
    private ArrayList<SchoolNewsSend.ReceiverBean> alreadyReads = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_list);
        ButterKnife.bind(this);
        setTopName("列表");
        context = this;
        getData();
        setContent();
    }

    private void setContent() {
        activityReadListTvWeidu.setText(notReads.size()+"");
        activityReadListTvYidu.setText(alreadyReads.size()+"");
        activityReadListLvWeidu.setAdapter(new CommonAdapter<SchoolNewsSend.ReceiverBean>(context, notReads, R.layout.item_read_info) {
            @Override
            public void convert(ViewHolder holder, SchoolNewsSend.ReceiverBean receiverBean) {
                holder.setText(R.id.item_read_info_tv_name, receiverBean.getReceiver_user_name());
                holder.setImageByUrl(R.id.item_read_info_iv_avatar, receiverBean.getPhoto());
            }
        });
        activityReadListLvYidu.setAdapter(new CommonAdapter<SchoolNewsSend.ReceiverBean>(context,alreadyReads,R.layout.item_read_info) {
            @Override
            public void convert(ViewHolder holder, SchoolNewsSend.ReceiverBean receiverBean) {
                holder.setText(R.id.item_read_info_tv_name, receiverBean.getReceiver_user_name());
                holder.setImageByUrl(R.id.item_read_info_iv_avatar, receiverBean.getPhoto());
            }
        });

    }

    /**
     * 根据intent接收值
     */
    private void getData() {
        alreadyReads = (ArrayList<SchoolNewsSend.ReceiverBean>) getIntent().getSerializableExtra("yidu");
        notReads = (ArrayList<SchoolNewsSend.ReceiverBean>) getIntent().getSerializableExtra("weidu");
    }

    @Override
    public void requsetData() {

    }
}
