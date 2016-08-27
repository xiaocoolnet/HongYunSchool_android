package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.PhotoWithPath;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.GalleryFinalUtil;
import cn.xiaocool.hongyunschool.utils.GetImageUtil;
import cn.xiaocool.hongyunschool.utils.ImgLoadUtil;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.activity_personal_info_iv_avatar)
    CircleImageView activityPersonalInfoIvAvatar;
    @BindView(R.id.activity_personal_info_rl_avatar)
    RelativeLayout activityPersonalInfoRlAvatar;
    @BindView(R.id.activity_personal_info_tv_name)
    TextView activityPersonalInfoTvName;
    @BindView(R.id.activity_personal_info_rl_name)
    RelativeLayout activityPersonalInfoRlName;
    @BindView(R.id.activity_personal_info_tv_sex)
    TextView activityPersonalInfoTvSex;
    @BindView(R.id.activity_personal_info_rl_sex)
    RelativeLayout activityPersonalInfoRlSex;
    @BindView(R.id.activity_personal_info_tv_phone)
    TextView activityPersonalInfoTvPhone;
    @BindView(R.id.activity_personal_info_rl_phone)
    RelativeLayout activityPersonalInfoRlPhone;
    @BindView(R.id.activity_personal_info_rl_changepsw)
    RelativeLayout activityPersonalInfoRlChangepsw;
    private Context context;
    private ArrayList<PhotoInfo> mPhotoList;
    private ArrayList<PhotoWithPath> photoWithPaths;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private GalleryFinalUtil galleryFinalUtil;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x110:
                    if(msg.obj!=null){
                        if (JsonResult.JSONparser(context, String.valueOf(msg.obj))){
                            finish();
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        context = this;
        mPhotoList = new ArrayList<>();
        photoWithPaths = new ArrayList<>();
        galleryFinalUtil = new GalleryFinalUtil(1);
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_personal_info_rl_avatar, R.id.activity_personal_info_rl_name, R.id.activity_personal_info_rl_sex, R.id.activity_personal_info_rl_changepsw})
    public void onClick(View view) {
        switch (view.getId()) {
            //修改头像
            case R.id.activity_personal_info_rl_avatar:
                showActionSheet();
                break;
            //修改名字
            case R.id.activity_personal_info_rl_name:
                break;
            //修改性别
            case R.id.activity_personal_info_rl_sex:
                break;
            //修改密码
            case R.id.activity_personal_info_rl_changepsw:
                startActivity(ChangePswActivity.class);
                break;
        }
    }

    /**
     * 相册拍照弹出框
     */
    private void showActionSheet() {
        ActionSheet.createBuilder(context, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

                        switch (index) {
                            case 0:
                                galleryFinalUtil.openAblum(context, mPhotoList, REQUEST_CODE_GALLERY, mOnHanlderResultCallback);
                                break;
                            case 1:
                                //获取拍照权限
                                if (galleryFinalUtil.openCamera(context, mPhotoList, REQUEST_CODE_CAMERA, mOnHanlderResultCallback)) {
                                    return;
                                } else {
                                    String[] perms = {"android.permission.CAMERA"};
                                    int permsRequestCode = 200;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(perms, permsRequestCode);
                                    }
                                }
                                break;

                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    /**
     * 选择图片后 返回的图片数据
     */

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                photoWithPaths.clear();
                mPhotoList.clear();
                mPhotoList.addAll(resultList);
                photoWithPaths.addAll(GetImageUtil.getImgWithPaths(resultList));
                ImgLoadUtil.display("file:/" + mPhotoList.get(0).getPhotoPath(), activityPersonalInfoIvAvatar);
            }
        }
        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 授权权限
     * @param permsRequestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean cameraAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                if(cameraAccepted){
                    //授权成功之后，调用系统相机进行拍照操作等
                    galleryFinalUtil.openCamera(context, mPhotoList, REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                }else{
                    //用户授权拒绝之后，友情提示一下就可以了
                    ToastUtil.showShort(this, "已拒绝进入相机，如想开启请到设置中开启！");
                }

                break;

        }

    }
}
