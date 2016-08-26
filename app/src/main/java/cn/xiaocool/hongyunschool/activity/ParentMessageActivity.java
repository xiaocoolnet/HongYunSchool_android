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
import cn.xiaocool.hongyunschool.bean.WebListInfo;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ViewHolder;

public class ParentMessageActivity extends BaseActivity {


    @BindView(R.id.web_parent_lv)
    ListView webParentLv;
    @BindView(R.id.web_parent_swip)
    SwipeRefreshLayout webParentSwip;

    private CommonAdapter adapter;
    private ArrayList<WebListInfo> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_message);
        ButterKnife.bind(this);
        listData = new ArrayList<>();
        setTopName("家长信箱");
        setRightText("反馈").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddParentMessageActivity.class);
            }
        });

        settingRefresh();
    }

    /**
     * 设置
     */
    private void settingRefresh() {
        webParentSwip.setColorSchemeResources(R.color.white);
        webParentSwip.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.themeColor));
        webParentSwip.setProgressViewOffset(true, 10, 100);
        webParentSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requsetData();
            }
        });
    }
    @Override
    public void requsetData() {
        String url = "";
        VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(ParentMessageActivity.this, result)) {
                    listData.clear();
                    listData.addAll(getBeanFromJson(result));
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
        listData.clear();
        listData.addAll(getBeanFromJson(result));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<WebListInfo>(getBaseContext(), listData, R.layout.item_parent_message) {
                @Override
                public void convert(ViewHolder holder, WebListInfo datas) {
                    setItem(holder, datas);
                }
            };
            webParentLv.setAdapter(adapter);
        }
    }


    /**
     * 对item 操作
     * @param holder
     * @param datas
     */
    private void setItem(ViewHolder holder, WebListInfo datas) {

        holder.getView(R.id.item_pn_remark_layout).setVisibility(View.GONE);

    }
    /**
     * 字符串转模型
     *
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
