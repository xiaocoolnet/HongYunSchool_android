package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.WebOther;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.utils.ViewHolder;
import cn.xiaocool.hongyunschool.view.CustomHeader;

public class WebOtherListActivity extends BaseActivity {

    @BindView(R.id.web_list)
    ListView webList;
    @BindView(R.id.web_list_swip)
    XRefreshView webListSwip;

    private Context context;
    private List<WebOther> webOthers;
    private CommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_other_list);
        ButterKnife.bind(this);
        context = this;
        webOthers = new ArrayList<>();
        settingRefresh();
    }

    /**
     * 设置
     */
    private void settingRefresh() {
        webListSwip.setPullRefreshEnable(true);
        webListSwip.setPullLoadEnable(true);
        webListSwip.setCustomHeaderView(new CustomHeader(this,2000));
        webListSwip.setAutoRefresh(true);
        webListSwip.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh() {
                requsetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webListSwip.stopRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        webListSwip.stopLoadMore();
                    }
                }, 2000);
            }

            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
            }
        });

        setTopName(getIntent().getStringExtra("title") != null ? getIntent().getStringExtra("title") : "列表");

        webList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title",getIntent().getStringExtra("title"));
                bundle.putSerializable(LocalConstant.WEB_FLAG_FIVE, webOthers.get(position));
                startActivity(FivePublicWebDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void requsetData() {
        String url = "";
        String schoolid = SPUtils.get(context,LocalConstant.SCHOOL_ID,"1").toString();
        switch (getIntent().getStringExtra("title")){
            case "学校作业":
                url = NetConstantUrl.FIVE_PUBLIC_HOMEWORK +schoolid;
                break;
            case "校本课程":
                url = NetConstantUrl.FIVE_PUBLIC_SUBJECT1+schoolid;
                break;
            case "选修课程":
                url = NetConstantUrl.FIVE_PUBLIC_SUBJECT2+schoolid;
                break;
            case "课时":
                url = NetConstantUrl.FIVE_PUBLIC_SUBJECT_TIME+schoolid;
                break;
            case "期末检测":
                url = NetConstantUrl.FIVE_PUBLIC_END+schoolid;
                break;
            case "节假日":
                url = NetConstantUrl.FIVE_PUBLIC_HOLIDAY+schoolid;
                break;
        }
        VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(WebOtherListActivity.this, result)) {
                    webListSwip.stopRefresh();
                    webListSwip.stopLoadMore();
                    webOthers.clear();
                    webOthers.addAll(getBeanFromJson(result));
                    adapter = new CommonAdapter<WebOther>(context,webOthers,R.layout.item_other_web) {
                        @Override
                        public void convert(ViewHolder holder, WebOther webOther) {
                            holder.setText(R.id.title,webOther.getPost_title());
                        }
                    };
                    webList.setAdapter(adapter);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<WebOther> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<WebOther>>() {
        }.getType());
    }
}
