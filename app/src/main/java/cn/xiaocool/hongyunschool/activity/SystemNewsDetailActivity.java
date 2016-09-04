package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.SystemNews;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.ToastUtil;
import cn.xiaocool.hongyunschool.view.SharePopupWindow;

public class SystemNewsDetailActivity extends BaseActivity {

    @BindView(R.id.swd_webview)
    WebView swdWebview;
    private SystemNews systemNews;
    SharePopupWindow takePhotoPopWin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_web_detail);
        ButterKnife.bind(this);

        systemNews = (SystemNews) getIntent().getSerializableExtra(LocalConstant.WEB_FLAG);
        setTopName(systemNews.getPost_title());
        setRightText("分享").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopFormBottom(v);
            }
        });
    }

    public void showPopFormBottom(View view) {
        takePhotoPopWin = new SharePopupWindow(this, onClickListener);
        //SharePopupWindow takePhotoPopWin = new SharePopupWindow(this, onClickListener);
        takePhotoPopWin.showAtLocation(swdWebview, Gravity.BOTTOM, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.haoyou:
                    ToastUtil.showShort(SystemNewsDetailActivity.this, "微信好友");
                    setting();
                    break;
                case R.id.dongtai:
                    ToastUtil.showShort(SystemNewsDetailActivity.this, "微信朋友圈");
                    history();
                    break;
            }
        }
    };
    @Override
    public void requsetData() {
        String schoolid = systemNews.getId();

        String url = NetConstantUrl.SYSTEM_H5 + schoolid;
        swdWebview.loadUrl(url);
        swdWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回的是true，说明是使用webview打开的网页。否则使用系统默认的浏览器
                return true;
            }
        });

    }

    /**
     * 分享到微信好友
     */
    private void setting() {
        //ToastUtils.ToastShort(this, "分享到微信好友");
//        flag = 0;
        shareWX();
        takePhotoPopWin.dismiss();

    }

    /**
     * 分享到微信朋友圈
     */
    private void history() {
        // ToastUtils.ToastShort(this, "分享到微信朋友圈");
//        flag = 1;
        shareWX();
        takePhotoPopWin.dismiss();
    }



    /**
     * 微信分享网页
     * */
    private void shareWX() {
//        //创建一个WXWebPageObject对象，用于封装要发送的Url
//        WXWebpageObject webpage =new WXWebpageObject();
//        webpage.webpageUrl=NetBaseConstant.NET_H5_HOST + "&a="+a+"&id="+itemid;
//        WXMediaMessage msg =new WXMediaMessage(webpage);
//        msg.title=title;
//        msg.description=content;
//        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share_wx);
//        msg.setThumbImage(thumb);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = "weiyi";
//        req.message = msg;
//        req.scene = flag==0? SendMessageToWX.Req.WXSceneSession: SendMessageToWX.Req.WXSceneTimeline;
//        WxtApplication wxtApplication = WxtApplication.getInstance();
//        wxtApplication.api.sendReq(req);
    }

}
