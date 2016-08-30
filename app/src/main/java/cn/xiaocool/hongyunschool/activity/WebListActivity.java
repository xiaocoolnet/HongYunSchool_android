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
import cn.xiaocool.hongyunschool.bean.WebListInfo;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class WebListActivity extends BaseActivity {

    @BindView(R.id.web_list)
    ListView webList;
    @BindView(R.id.web_list_swip)
    SwipeRefreshLayout webListSwip;

    private ArrayList<WebListInfo> webListInfoArrayList;
    private CommonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_list);
        ButterKnife.bind(this);
        webListInfoArrayList = new ArrayList<>();
        settingRefresh();
    }


    /**
     * 设置
     */
    private void settingRefresh() {
        webListSwip.setColorSchemeResources(R.color.white);
        webListSwip.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.themeColor));
        webListSwip.setProgressViewOffset(true, 10, 100);
        webListSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requsetData();
            }
        });

        setTopName(getIntent().getStringExtra("title")!=null?getIntent().getStringExtra("title"):"列表");
    }
    @Override
    public void requsetData() {

        String url = "http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfos&schoolid=1";

        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfos&schoolid=1    教师风采
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getbabyinfos&schoolid=1       学生秀场
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getWebSchoolInfos&schoolid=1  学校介绍
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getInterestclass&schoolid=1   精彩活动


        VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(WebListActivity.this,result)){
                    webListInfoArrayList.clear();
                    webListInfoArrayList.addAll(getBeanFromJson(result));
                    setAdapter();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setAdapter() {
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }else {
            adapter = new CommonAdapter<WebListInfo>(this,webListInfoArrayList,R.layout.item_web_list) {
                @Override
                public void convert(ViewHolder holder, WebListInfo webListInfo) {
                    holder.setImageResource(R.id.teacher_img,R.drawable.hyx_default)
                            .setText(R.id.post_title,webListInfo.getPost_title())
                            .setText(R.id.post_content,webListInfo.getPost_excerpt())
                            .setText(R.id.post_date,webListInfo.getPost_date());
                }

            };
            webList.setAdapter(adapter);
        }
    }


    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<WebListInfo> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<WebListInfo>>() {
        }.getType());
    }
}
