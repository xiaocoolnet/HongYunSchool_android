package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import cn.xiaocool.hongyunschool.bean.ShortMessage;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class MessageActivity extends BaseActivity {


    @BindView(R.id.school_news_lv)
    ListView schoolNewsLv;
    @BindView(R.id.school_news_srl)
    SwipeRefreshLayout schoolNewsSrl;

    private CommonAdapter adapter;
    private List<ShortMessage> shortMessages;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);
        ButterKnife.bind(this);
        shortMessages = new ArrayList<>();
        context = this;
        setTopName("短信列表");
        setRightImg(R.drawable.ic_fabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddGroupMessageActivity.class);
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
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        schoolNewsSrl.setRefreshing(false);
                    }
                }, 5000);
            }
        });

    }

    @Override
    public void requsetData() {
        String url = NetConstantUrl.GET_SHORT_MESSAGE + SPUtils.get(context, LocalConstant.USER_ID,"");
        VolleyUtil.VolleyGetRequest(this, url, new
                VolleyUtil.VolleyJsonCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if (JsonResult.JSONparser(getBaseContext(), result)) {
                            schoolNewsSrl.setRefreshing(false);
                            setAdapter(result);
                        }else {
                            schoolNewsSrl.setRefreshing(false);
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
        shortMessages.clear();
        shortMessages.addAll(getBeanFromJson(result));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<ShortMessage>(getBaseContext(), shortMessages, R.layout.short_message_item) {
                @Override
                public void convert(ViewHolder holder, ShortMessage datas) {
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
    private void setItem(ViewHolder holder, ShortMessage datas) {

        holder.setText(R.id.item_sn_content, datas.getMessage_content())
                .setTimeText(R.id.item_sn_time,datas.getCreate_time())
                .setText(R.id.item_sn_nickname,datas.getName())
                .setImageByUrl(R.id.item_sn_head_iv,datas.getPhoto());
    }

    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<ShortMessage> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<ShortMessage>>() {
        }.getType());
    }
}
