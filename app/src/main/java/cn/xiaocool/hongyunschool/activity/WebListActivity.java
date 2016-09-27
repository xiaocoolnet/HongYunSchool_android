package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import cn.xiaocool.hongyunschool.bean.SystemNews;
import cn.xiaocool.hongyunschool.bean.WebListInfo;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.ImgLoadUtil;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class WebListActivity extends BaseActivity {

    @BindView(R.id.web_list)
    ListView webList;
    @BindView(R.id.web_list_swip)
    SwipeRefreshLayout webListSwip;

    private ArrayList<WebListInfo> webListInfoArrayList;
    private ArrayList<SystemNews> systemNewses;
    private CommonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_list);
        ButterKnife.bind(this);
        webListInfoArrayList = new ArrayList<>();
        systemNewses = new ArrayList<>();
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
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webListSwip.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        setTopName(getIntent().getStringExtra("title") != null ? getIntent().getStringExtra("title") : "列表");

        webList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if (getIntent().getStringExtra(LocalConstant.WEB_FLAG).equals(LocalConstant.SYSTEN_NEWS)) {
                    bundle.putSerializable(LocalConstant.WEB_FLAG,systemNewses.get(position));
                    startActivity(SystemNewsDetailActivity.class,bundle);
                } else {
                    webListInfoArrayList.get(position).setWhere(getIntent().getStringExtra(LocalConstant.WEB_FLAG));
                    bundle.putSerializable(LocalConstant.WEB_FLAG, webListInfoArrayList.get(position));
                    startActivity(SchoolWebDetailActivity.class, bundle);
                }
            }
    });
    }
    @Override
    public void requsetData() {

        String schoolid = (String) SPUtils.get(this,LocalConstant.SCHOOL_ID,"1");

        String url = "";

        switch (getIntent().getStringExtra(LocalConstant.WEB_FLAG)){
            case LocalConstant.WEB_INTROUCE:
                url = NetConstantUrl.GET_WEB_SCHOOL_INTROUCE + schoolid;
                break;
            case LocalConstant.WEB_TEACHER:
                url = NetConstantUrl.GET_WEB_SCHOOL_TEACHER + schoolid;
                break;
            case LocalConstant.WEB_STUDENT:
                url = NetConstantUrl.GET_WEB_SCHOOL_STUDENT + schoolid;
                break;
            case LocalConstant.WEB_ACTIVITY:
                url = NetConstantUrl.GET_WEB_SCHOOL_ACTIVITY + schoolid;
                break;
            case LocalConstant.WEB_NOTICE:
                url = NetConstantUrl.GET_WEB_SCHOOL_NOTICE + schoolid;
                break;
            case LocalConstant.WEB_NEWS:
                url = NetConstantUrl.GET_WEB_SCHOOL_NEWS + schoolid;
                break;
            case LocalConstant.SYSTEN_NEWS:
                url = NetConstantUrl.GET_WEB_SYSTEM_NEWS;
                break;
        }
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfos&schoolid=1    教师风采
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getbabyinfos&schoolid=1       学生秀场
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getWebSchoolInfos&schoolid=1  学校介绍
        //http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getInterestclass&schoolid=1   精彩活动
        //系统消息
        if(getIntent().getStringExtra(LocalConstant.WEB_FLAG).equals(LocalConstant.SYSTEN_NEWS)){
            VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
                @Override
                public void onSuccess(String result) {
                    if (JsonResult.JSONparser(WebListActivity.this, result)) {
                        systemNewses.clear();
                        systemNewses.addAll(getBeanFromJsonSystem(result));
                        webListSwip.setRefreshing(false);
                        setSystemAdapter();
                    }else {
                        webListSwip.setRefreshing(false);
                    }
                }

                @Override
                public void onError() {

                }
            });
        }else{
            VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
                @Override
                public void onSuccess(String result) {
                    if (JsonResult.JSONparser(WebListActivity.this, result)) {
                        webListInfoArrayList.clear();
                        webListInfoArrayList.addAll(getBeanFromJson(result));
                        webListSwip.setRefreshing(false);
                        setAdapter();
                    }else {
                        webListSwip.setRefreshing(false);
                    }
                }

                @Override
                public void onError() {

                }
            });
        }

    }

    private void setAdapter() {
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }else {
            adapter = new CommonAdapter<WebListInfo>(this,webListInfoArrayList,R.layout.item_web_list) {
                @Override
                public void convert(ViewHolder holder, WebListInfo webListInfo) {
                    holder.setText(R.id.post_title,webListInfo.getPost_title())
                            .setText(R.id.post_content,webListInfo.getPost_excerpt())
                            .setText(R.id.post_date,webListInfo.getPost_date());
                    if (webListInfo.getThumb().equals("")){
                        holder.getView(R.id.teacher_img).setVisibility(View.GONE);
                    }else {
                        holder.getView(R.id.teacher_img).setVisibility(View.VISIBLE);
                        ImgLoadUtil.display(NetConstantUrl.WEB_IMAGE_URL+webListInfo.getThumb(), (ImageView)holder.getView(R.id.teacher_img));
                    }
                }

            };
            webList.setAdapter(adapter);
        }
    }

    private void setSystemAdapter() {
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }else {
            adapter = new CommonAdapter<SystemNews>(this,systemNewses,R.layout.item_web_list) {
                @Override
                public void convert(ViewHolder holder, SystemNews webListInfo) {
                    holder.setText(R.id.post_title, webListInfo.getPost_title())
                            .setText(R.id.post_content,webListInfo.getPost_excerpt())
                            .setText(R.id.post_date,webListInfo.getPost_date());
                    if (webListInfo.getThumb().equals("")){
                        holder.getView(R.id.teacher_img).setVisibility(View.GONE);
                    }else {
                        holder.getView(R.id.teacher_img).setVisibility(View.VISIBLE);
                        ImgLoadUtil.display(NetConstantUrl.WEB_IMAGE_URL+webListInfo.getThumb(), (ImageView)holder.getView(R.id.teacher_img));
                    }
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

    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<SystemNews> getBeanFromJsonSystem(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<SystemNews>>() {
        }.getType());
    }
}
