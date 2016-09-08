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
import cn.xiaocool.hongyunschool.bean.ClassNewsReceive;
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
public class ClassNewsFragment extends Fragment {


    @BindView(R.id.school_news_lv)
    ListView schoolNewsLv;
    @BindView(R.id.school_news_srl)
    SwipeRefreshLayout schoolNewsSrl;
    private CommonAdapter adapter;
    private List<ClassNewsReceive> classNewsReceives;
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
        classNewsReceives = new ArrayList<>();
        settingRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
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

    /**
     * 加载数据
     */
    private void getData() {
        String url = NetConstantUrl.GET_CLASS_NEWS_RECEIVE + "&receiverid=" + SPUtils.get(context, LocalConstant.USER_BABYID, "").toString();
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
            classNewsReceives.clear();
            classNewsReceives.addAll(getBeanFromJsonReceive(result));
            Collections.sort(classNewsReceives, new Comparator<ClassNewsReceive>() {
                @Override
                public int compare(ClassNewsReceive lhs, ClassNewsReceive rhs) {
                    return (int) (Long.parseLong(rhs.getHomework_info().get(0).getCreate_time()) - Long.parseLong(lhs.getHomework_info().get(0).getCreate_time()));
                }
            });
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new CommonAdapter<ClassNewsReceive>(context, classNewsReceives, R.layout.school_announcement_item) {
                    @Override
                    public void convert(ViewHolder holder, ClassNewsReceive datas) {
                        setItemReceive(holder, datas);
                    }
                };
                schoolNewsLv.setAdapter(adapter);
            }

    }

    /**
     * 对item 操作(家长)
     * @param holder
     * @param datas
     */
    private void setItemReceive(ViewHolder holder, ClassNewsReceive datas) {

        //获取图片字符串数组
        ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<datas.getPicture().size();i++){
            images.add(datas.getPicture().get(i).getPicture_url());
        }

        //判断已读和未读

        final ArrayList<ClassNewsReceive.ReceiveListBean> notReads = new ArrayList<>();
        final ArrayList<ClassNewsReceive.ReceiveListBean> alreadyReads = new ArrayList<>();
        if (datas.getReceive_list().size()>0){
            for (int i=0;i<datas.getReceive_list().size();i++){
                if (datas.getReceive_list().get(i).getRead_time()==null||datas.getReceive_list().get(i).getRead_time().equals("null")){
                    notReads.add(datas.getReceive_list().get(i));
                }else {
                    alreadyReads.add(datas.getReceive_list().get(i));
                }
            }
        }

        holder.setText(R.id.item_sn_content, datas.getHomework_info().get(0).getContent())
                .setTimeText(R.id.item_sn_time, datas.getHomework_info().get(0).getCreate_time())
                .setText(R.id.item_sn_nickname, datas.getHomework_info().get(0).getName())
                .setItemImages(context, R.id.item_sn_onepic, R.id.item_sn_gridpic, images)
                .setImageByUrl(R.id.item_sn_head_iv, datas.getHomework_info().get(0).getPhoto())
                .setText(R.id.item_sn_read, "总发" + datas.getReceive_list().size() + " 已读" + alreadyReads.size() + " 未读" + notReads.size());

    }

    /**
     * 字符串转模型(家长)
     * @param result
     * @return
     */
    private List<ClassNewsReceive> getBeanFromJsonReceive(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<ClassNewsReceive>>() {
        }.getType());
    }
}
