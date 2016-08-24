package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import cn.xiaocool.hongyunschool.bean.SchoolNews;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class SchoolNewsActivity extends BaseActivity {


    @BindView(R.id.school_news_lv)
    ListView schoolNewsLv;
    @BindView(R.id.school_news_srl)
    SwipeRefreshLayout schoolNewsSrl;

    private CommonAdapter adapter;
    private List<SchoolNews> schoolNewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);
        ButterKnife.bind(this);
        schoolNewsList = new ArrayList<>();
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
        schoolNewsList.addAll(getBeanFromJson(result));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<SchoolNews>(getBaseContext(), schoolNewsList, R.layout.school_news_item) {
                @Override
                public void convert(ViewHolder holder, SchoolNews datas) {
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
    private void setItem(ViewHolder holder, SchoolNews datas) {

        //获取图片字符串数组
        ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<datas.getPicture().size();i++){
            images.add(datas.getPicture().get(i).getPicture_url());
        }

        holder.setText(R.id.item_sn_content, datas.getMessage_content())
        .setTimeText(R.id.item_sn_time,datas.getMessage_time())
        .setText(R.id.item_sn_nickname,datas.getSend_user_name())
        .setItemImages(this,R.id.item_sn_onepic,R.id.item_sn_gridpic,images);

    }

    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<SchoolNews> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<SchoolNews>>() {
        }.getType());
    }
}
