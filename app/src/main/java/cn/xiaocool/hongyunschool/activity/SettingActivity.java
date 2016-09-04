package cn.xiaocool.hongyunschool.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.app.MyApplication;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.view.NiceDialog;
import cn.xiaocool.hongyunschool.view.update.UpdateService;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.activity_setting_rl_help)
    RelativeLayout activitySettingRlHelp;
    @BindView(R.id.activity_setting_rl_feedback)
    RelativeLayout activitySettingRlFeedback;
    @BindView(R.id.activity_setting_aboutUs)
    RelativeLayout activitySettingAboutUs;
    @BindView(R.id.activity_setting_rl_update)
    RelativeLayout activitySettingRlUpdate;
    @BindView(R.id.activity_setting_rl_clean)
    RelativeLayout activitySettingRlClean;
    @BindView(R.id.activity_setting_tv_quit)
    TextView activitySettingTvQuit;
    private Context context;



    private static final int REQUEST_WRITE_STORAGE = 111;
    private NiceDialog mDialog = null;
    //apk下载链接
    private static final String APK_DOWNLOAD_URL = "http://app.xiaomi.com/download/421138?ref=search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        context = this;
        setTopName("设置");
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_setting_rl_help, R.id.activity_setting_rl_feedback, R.id.activity_setting_aboutUs, R.id.activity_setting_rl_update, R.id.activity_setting_rl_clean, R.id.activity_setting_tv_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            //使用帮助
            case R.id.activity_setting_rl_help:
                break;
            //意见反馈
            case R.id.activity_setting_rl_feedback:
                break;
            //关于我们
            case R.id.activity_setting_aboutUs:
                break;
            //版本更新
            case R.id.activity_setting_rl_update:
                chechVersion();
                break;
            //清除缓存
            case R.id.activity_setting_rl_clean:
                break;
            //退出
            case R.id.activity_setting_tv_quit:
                SPUtils.clear(context);
                startActivity(LoginActivity.class);
                MyApplication.getInstance().onTerminate();
                break;
        }
    }

    private void chechVersion() {
        mDialog = new NiceDialog(SettingActivity.this);
        mDialog.setTitle("发现新版本");
        mDialog.setContent("为了给大家提供更好的用户体验，每次应用的更新都包含速度和稳定性的提升，感谢您的使用！");
        mDialog.setOKButton("立即更新", new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //请求存储权限
                boolean hasPermission = (ContextCompat.checkSelfPermission(SettingActivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermission) {
                    ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
                    ActivityCompat.shouldShowRequestPermissionRationale(SettingActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    //下载
                    startDownload();
                }

            }
        });
        mDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到存储权限,进行下载
                    startDownload();
                } else {
                    Toast.makeText(SettingActivity.this, "不授予存储权限将无法进行下载!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 启动下载
     */
    private void startDownload() {
        Intent it = new Intent(SettingActivity.this, UpdateService.class);
        //下载地址
        it.putExtra("apkUrl", APK_DOWNLOAD_URL);
        startService(it);
        mDialog.dismiss();
    }
}
