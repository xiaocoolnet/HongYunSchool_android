package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.SchoolNewsSend;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ToastUtil;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class MessageActivity extends BaseActivity {


    @BindView(R.id.school_news_lv)
    ListView schoolNewsLv;
    @BindView(R.id.school_news_srl)
    SwipeRefreshLayout schoolNewsSrl;

    private CommonAdapter adapter;
    private List<SchoolNewsSend> schoolNewsSendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);
        ButterKnife.bind(this);
        schoolNewsSendList = new ArrayList<>();
        setTopName("校内通知");
        setRightImg(R.drawable.ic_fabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddSchoolNewsActivity.class);
                ToastUtil.Toast(getBaseContext(), "发布");

            }
        });
        settingRefresh();
    }



    /**
     * 设置
     */
    private void settingRefresh() {
        schoolNewsSrl.setColorSchemeResources(R.color.white);
        schoolNewsSrl.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.themeColor));
        schoolNewsSrl.setProgressViewOffset(true, 10, 100);
        schoolNewsSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requsetData();
            }
        });
    }

    @Override
    public void requsetData() {
        VolleyUtil.VolleyGetRequest(this, "http://wxt.xiaocool.net/index.php?g=Apps&m=Message&a=user_send_message&send_user_id=605", new
                VolleyUtil.VolleyJsonCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if (JsonResult.JSONparser(getBaseContext(), result)) {
                            schoolNewsSrl.setRefreshing(false);
                            setAdapter(result);
                        }
                    }
                    @Override
                    public void onError() {

                    }
                });
    }

    /**
     * list 设置adapter
     * @param result
     */
    private void setAdapter(String result) {
        schoolNewsSendList.clear();
        schoolNewsSendList.addAll(getBeanFromJson(result));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<SchoolNewsSend>(getBaseContext(), schoolNewsSendList, R.layout.school_news_item) {
                @Override
                public void convert(ViewHolder holder, SchoolNewsSend datas) {
                    setItem(holder, datas);
                }
            };
            schoolNewsLv.setAdapter(adapter);
        }
    }

    /**
     * 对item 操作
     * @param holder
     * @param datas
     */
    private void setItem(ViewHolder holder, SchoolNewsSend datas) {

        //获取图片字符串数组
        ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<datas.getPicture().size();i++){
            images.add(datas.getPicture().get(i).getPicture_url());
        }

        //判断已读和未读

        final ArrayList<SchoolNewsSend.ReceiverBean> notReads = new ArrayList<>();
        final ArrayList<SchoolNewsSend.ReceiverBean> alreadyReads = new ArrayList<>();
        if (datas.getReceiver().size()>0){
            for (int i=0;i<datas.getReceiver().size();i++){
                if (datas.getReceiver().get(i).getRead_time()==null||datas.getReceiver().get(i).getRead_time().equals("null")){
                    notReads.add(datas.getReceiver().get(i));
                }else {
                    alreadyReads.add(datas.getReceiver().get(i));
                }
            }
        }

        holder.setText(R.id.item_sn_content, datas.getMessage_content())
                .setTimeText(R.id.item_sn_time,datas.getMessage_time())
                .setText(R.id.item_sn_nickname,datas.getSend_user_name())
                .setItemImages(this,R.id.item_sn_onepic,R.id.item_sn_gridpic,images)
                .setText(R.id.item_sn_read,"总发" + datas.getReceiver().size()+" 已读"+alreadyReads.size()+" 未读"+notReads.size());

        //进入已读未读界面
        holder.getView(R.id.item_sn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(MessageActivity.this, "进入已读未读!");
            }
        });
    }

    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<SchoolNewsSend> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<SchoolNewsSend>>() {
        }.getType());
    }
}
