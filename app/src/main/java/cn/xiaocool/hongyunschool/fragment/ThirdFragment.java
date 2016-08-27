package cn.xiaocool.hongyunschool.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.activity.ImageDetailActivity;
import cn.xiaocool.hongyunschool.activity.PostTrendActivity;
import cn.xiaocool.hongyunschool.bean.Trends;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseFragment;
import cn.xiaocool.hongyunschool.utils.CommonAdapter;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.PopInputManager;
import cn.xiaocool.hongyunschool.utils.ToastUtil;
import cn.xiaocool.hongyunschool.utils.ViewHolder;
import cn.xiaocool.hongyunschool.view.CommentPopupWindow;
import cn.xiaocool.hongyunschool.view.RefreshLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends BaseFragment {


    @BindView(R.id.fragment_third_iv_send)
    ImageView fragmentThirdIvSend;
    @BindView(R.id.fragment_third_lv_trend)
    ListView fragmentThirdLvTrend;
    @BindView(R.id.fragment_third_srl_trend)
    RefreshLayout fragmentThirdSrlTrend;
    private Context context;
    private CommonAdapter adapter;
    private List<Trends> trendsList;
    private CommentPopupWindow commentPopupWindow;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        fragmentThirdSrlTrend.setColorSchemeResources(R.color.white);
        fragmentThirdSrlTrend.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.themeColor));
        fragmentThirdSrlTrend.setProgressViewOffset(true, 10, 100);
        fragmentThirdSrlTrend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragmentThirdSrlTrend.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        initData();
                        //关闭动画
                        fragmentThirdSrlTrend.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        //上拉加载
        fragmentThirdSrlTrend.setOnLoadListener(new RefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {
                fragmentThirdSrlTrend.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ToastUtil.showShort(context, "上拉加载");
                        fragmentThirdSrlTrend.setLoading(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void initData() {
        VolleyUtil.VolleyGetRequest(context, "http://wxt.xiaocool.net/index.php?g=apps&m=index&a=GetMicroblog&userid=681&classid=1&schoolid=1&type=1&beginid=0", new
                VolleyUtil.VolleyJsonCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if (JsonResult.JSONparser(context, result)) {
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
        trendsList = new ArrayList<>();
        trendsList.addAll(getBeanFromJson(result));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new CommonAdapter<Trends>(context, trendsList, R.layout.trend_item) {
                @Override
                public void convert(ViewHolder holder, Trends datas) {
                    setItem(holder, datas);
                }
            };
            fragmentThirdLvTrend.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        context = getActivity();
        return rootView;
    }

    /**
     * 对item 操作
     * @param holder
     * @param datas
     */
    private void setItem(ViewHolder holder, Trends datas) {

        //获取图片字符串数组
        final ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<datas.getPic().size();i++){
            images.add(datas.getPic().get(i).getPictureurl());
        }

        holder.setImageByUrl(R.id.trend_item_iv_avatar,datas.getPhoto())
                .setText(R.id.trend_item_tv_content, datas.getContent())
                .setText(R.id.trend_item_tv_name,datas.getName())
                .setTimeText(R.id.trend_item_tv_time,datas.getWrite_time())
                .setText(R.id.trend_item_tv_praise,datas.getLike().size()+"")
                .setText(R.id.trend_item_tv_comment,datas.getComment().size()+"")
                .setItemImages(context, R.id.trend_item_iv_onepic, R.id.trend_item_gv_anypic, images);
        holder.getView(R.id.trend_item_iv_onepic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ImageDetailActivity.class);
                intent.putStringArrayListExtra("Imgs", images);
                intent.putExtra("position",0);
                intent.putExtra("type", "4");
                context.startActivity(intent);
            }
        });
        //显示评论
        ListView lv_comment = holder.getView(R.id.trend_item_lv_comment);
        lv_comment.setAdapter(new CommonAdapter<Trends.CommentBean>(context, datas.getComment(), R.layout.item_trend_comment) {
            @Override
            public void convert(ViewHolder holder, Trends.CommentBean commentBean) {
                holder.setImageByUrl(R.id.item_trend_comment_iv_avatar, commentBean.getAvatar())
                        .setText(R.id.item_trend_comment_tv_name, commentBean.getName())
                        .setText(R.id.item_trend_comment_tv_content, commentBean.getContent())
                        .setTimeText(R.id.item_trend_comment_tv_time, commentBean.getComment_time());
            }
        });
        //评论按钮
        holder.getView(R.id.trend_item_iv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment();
            }
        });
    }

    /**
     * 评论事件
     */
    private void comment() {
        commentPopupWindow = new CommentPopupWindow(context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_comment:
                        if (commentPopupWindow.ed_comment.getText().length() > 0) {
                            ToastUtil.showShort(context,"评论事件");
                            commentPopupWindow.dismiss();
                            commentPopupWindow.ed_comment.setText("");
                        } else {

                            Toast.makeText(context, "发送内容不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        final EditText editText = commentPopupWindow.ed_comment;
        commentPopupWindow.showAtLocation(fragmentThirdSrlTrend, Gravity.BOTTOM, 0, 0);
        //弹出系统输入法
        PopInputManager.popInput(editText);
    }


    /**
     * 字符串转模型
     * @param result
     * @return
     */
    private List<Trends> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<Trends>>() {
        }.getType());
    }
    @OnClick(R.id.fragment_third_iv_send)
    //发布动态
    public void onClick() {
        getActivity().startActivity(new Intent(getActivity(), PostTrendActivity.class));
    }
}
