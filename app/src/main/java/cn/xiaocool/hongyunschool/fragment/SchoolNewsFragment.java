package cn.xiaocool.hongyunschool.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.SchoolNewsReceiver;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.utils.ViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolNewsFragment extends Fragment {


    @BindView(R.id.school_news_lv)
    ListView schoolNewsLv;
    @BindView(R.id.school_news_srl)
    SwipeRefreshLayout schoolNewsSrl;
    private List<SchoolNewsReceiver> schoolNewsReceiverList;
    private List<SchoolNewsReceiver.ReceiveBean> receiveBeans;
    private CommonAdapter adapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_school_news, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        schoolNewsReceiverList = new ArrayList<>();
        receiveBeans = new ArrayList<>();
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
                getData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    /**
     * 加载数据
     */
    private void getData() {
        String url = NetConstantUrl.GET_SCHOOL_NEWS_RECEIVE + "&userid=" + SPUtils.get(context, LocalConstant.USER_ID, "");
        VolleyUtil.VolleyGetRequest(context, url, new
                VolleyUtil.VolleyJsonCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if (JsonResult.JSONparser(context, result)) {
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
        schoolNewsReceiverList.clear();
        schoolNewsReceiverList.addAll(getBeanFromJsonReceive(result));
        receiveBeans.addAll(changeBean(schoolNewsReceiverList));
        Collections.sort(receiveBeans, new Comparator<SchoolNewsReceiver.ReceiveBean>() {
            @Override
            public int compare(SchoolNewsReceiver.ReceiveBean lhs, SchoolNewsReceiver.ReceiveBean rhs) {
                return (int) (Long.parseLong(rhs.getSend_message().getMessage_time())-Long.parseLong(lhs.getSend_message().getMessage_time()));
            }
        });
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<SchoolNewsReceiver.ReceiveBean>(context, receiveBeans, R.layout.school_news_item) {
                @Override
                public void convert(ViewHolder holder, SchoolNewsReceiver.ReceiveBean datas) {
                    setReceiveItem(holder, datas);
                }
            };
            schoolNewsLv.setAdapter(adapter);
        }

    }

    /**
     * 对item 操作(接收)
     * @param holder
     * @param datas
     */
    private void setReceiveItem(ViewHolder holder, SchoolNewsReceiver.ReceiveBean datas) {

        ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<datas.getPic().size();i++){
            images.add(datas.getPic().get(i).getPicture_url());
        }

        holder.setText(R.id.item_sn_content, datas.getSend_message().getMessage_content())
                .setTimeText(R.id.item_sn_time, datas.getSend_message().getMessage_time())
                .setText(R.id.item_sn_nickname, datas.getSend_message().getSend_user_name())
                .setImageByUrl(R.id.item_sn_head_iv, datas.getSend_message().getPhoto())
                .setItemImages(context, R.id.item_sn_onepic, R.id.item_sn_gridpic, images);
    }

    /**
     * 字符串转模型（接收）
     * @param result
     * @return
     */
    private List<SchoolNewsReceiver> getBeanFromJsonReceive(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<SchoolNewsReceiver>>() {
        }.getType());
    }

    private List<SchoolNewsReceiver.ReceiveBean> changeBean(List<SchoolNewsReceiver> schoolNewsReceiverList) {
        List<SchoolNewsReceiver.ReceiveBean> receiver = new ArrayList<>();
        for(int i = 0;i<schoolNewsReceiverList.size();i++){
            receiver.addAll(schoolNewsReceiverList.get(i).getReceive());
        }
        return receiver;
    }
}
