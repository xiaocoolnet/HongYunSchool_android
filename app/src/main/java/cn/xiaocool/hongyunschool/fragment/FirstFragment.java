package cn.xiaocool.hongyunschool.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import cn.xiaocool.hongyunschool.activity.FivePublicActivity;
import cn.xiaocool.hongyunschool.activity.ParentMessageActivity;
import cn.xiaocool.hongyunschool.activity.WebListActivity;
import cn.xiaocool.hongyunschool.adapter.WebMaxThreeAdapter;
import cn.xiaocool.hongyunschool.bean.WebListInfo;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseFragment;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.view.ImageCycleView;
import cn.xiaocool.hongyunschool.view.NoScrollListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment {


    @BindView(R.id.first_top_name)
    TextView firstTopName;
    @BindView(R.id.announcement)
    RelativeLayout announcement;
    @BindView(R.id.web_gonggao_list)
    NoScrollListView webGonggaoList;
    @BindView(R.id.web_news_trends)
    RelativeLayout webNewsTrends;
    @BindView(R.id.web_news_list)
    NoScrollListView webNewsList;
    @BindView(R.id.icv_topView)
    ImageCycleView icvTopView;
    private int tag = 0;

    private ArrayList<WebListInfo> announceList;
    private ArrayList<WebListInfo> newsList;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    protected void initEvent() {
        super.initEvent();
        announceList = new ArrayList<>();
        newsList = new ArrayList<>();
        //设置标题
        firstTopName.setText("校网");
        //设置轮播图
        setLunBo();
    }

    private void setLunBo() {
        List<ImageCycleView.ImageInfo> list=new ArrayList<ImageCycleView.ImageInfo>();

        //res图片资源
        list.add(new ImageCycleView.ImageInfo(R.drawable.ll1, "今天天气不错", ""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.ll2, "风和日丽的", ""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.ll3, "今天下午没有课", ""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.ll4, "我觉得挺爽的", ""));

        showViewPager(list);
    }

    private void showViewPager(List<ImageCycleView.ImageInfo> list) {
        icvTopView.setAutoCycle(true); //自动播放
        icvTopView.setCycleDelayed(2000);//设置自动轮播循环时间
        icvTopView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

                //本地图片
                ImageView imageView = new ImageView(mActivity);
                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
                return imageView;


//				//使用SD卡图片
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageURI(Uri.fromFile((File)imageInfo.image));
//				return smartImageView;

//				//使用SmartImageView，既可以使用网络图片也可以使用本地资源
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//				return smartImageView;

                //使用BitmapUtils,只能使用网络图片
//				BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this);
//				ImageView imageView = new ImageView(MainActivity.this);
//				bitmapUtils.display(imageView, imageInfo.image.toString());
//				return imageView;


            }
        });

    }

    @Override
    public void initData() {
//http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getSchoolNotices&schoolid=1
        //getSchoolNews
        String announceUrl = "http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getSchoolNotices&schoolid=1";
        //获取校园公告
        VolleyUtil.VolleyGetRequest(mActivity, announceUrl, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(mActivity, result)) {
                    announceList.clear();
                    announceList.addAll(JsonParser(result));
                }
                setAnnounceAdapter();
            }

            @Override
            public void onError() {

            }
        });
        String newsUrl = "http://wxt.xiaocool.net/index.php?g=apps&m=school&a=getSchoolNews&schoolid=1";
        //获取新闻动态
        VolleyUtil.VolleyGetRequest(mActivity, newsUrl, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(mActivity, result)) {
                    newsList.clear();
                    newsList.addAll(JsonParser(result));
                }
                setNewsAdapter();
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     *新闻动态适配器
     */
    private void setNewsAdapter() {

        webGonggaoList.setAdapter(new WebMaxThreeAdapter(newsList,mActivity));
    }

    /**
     * 校园公告适配器
     */
    private void setAnnounceAdapter() {
        webNewsList.setAdapter(new WebMaxThreeAdapter(announceList,mActivity));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.web_rl_schoolintrouce, R.id.web_rl_teacher_style, R.id.web_rl_student_show, R.id.web_rl_five_public, R.id.web_rl_exciting_event, R.id.web_rl_parent_message})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.web_rl_schoolintrouce://学校介绍
                intent = new Intent(mActivity, WebListActivity.class);
                intent.putExtra("title","学校概况");
                startActivity(intent);
                break;
            case R.id.web_rl_teacher_style://教师风采
                intent = new Intent(mActivity, WebListActivity.class);
                intent.putExtra("title","教师风采");
                startActivity(intent);
                break;
            case R.id.web_rl_student_show://学生秀场
                intent = new Intent(mActivity, WebListActivity.class);
                intent.putExtra("title","学生秀场");
                startActivity(intent);
                break;
            case R.id.web_rl_five_public://五项公开专栏
                intent = new Intent(mActivity, FivePublicActivity.class);
                intent.putExtra("title","五项公开专栏");
                startActivity(intent);
                break;
            case R.id.web_rl_exciting_event://
                intent = new Intent(mActivity, WebListActivity.class);
                intent.putExtra("title","精彩活动");
                startActivity(intent);
                break;
            case R.id.web_rl_parent_message:
                intent = new Intent(mActivity, ParentMessageActivity.class);
                intent.putExtra("title","家长信箱");
                startActivity(intent);
                break;
        }
    }


    public  List<WebListInfo> JsonParser(String result) {
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
