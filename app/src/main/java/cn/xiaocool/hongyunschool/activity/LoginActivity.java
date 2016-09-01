package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.BabyInfo;
import cn.xiaocool.hongyunschool.bean.ClassInfo;
import cn.xiaocool.hongyunschool.bean.LoginReturn;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.JsonResult;
import cn.xiaocool.hongyunschool.utils.ProgressUtil;
import cn.xiaocool.hongyunschool.utils.SPUtils;
import cn.xiaocool.hongyunschool.utils.ToastUtil;
import cn.xiaocool.hongyunschool.view.CleanableEditText;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.activity_login_ed_phone)
    CleanableEditText activityLoginEdPhone;
    @BindView(R.id.activity_login_ed_psw)
    CleanableEditText activityLoginEdPsw;
    @BindView(R.id.activity_login_btn_login)
    TextView activityLoginBtnLogin;
    @BindView(R.id.activity_login_btn_forgetpsw)
    TextView activityLoginBtnForgetpsw;
    @BindView(R.id.login_rb_parent)
    RadioButton loginRbParent;
    @BindView(R.id.login_rb_teacher)
    RadioButton loginRbTeacher;
    @BindView(R.id.login_rg_licence)
    RadioGroup loginRgLicence;
    private Context context;
    private String type = "0";
    private LoginReturn loginReturn;
    private String isPrinsiple,isClassleader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTopName("泓云校");
        hideLeft();
        context = this;
        licenceIS();
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_login_btn_login, R.id.activity_login_btn_forgetpsw})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.activity_login_btn_login:
                login();
                ProgressUtil.showLoadingDialog(this);
                break;
            //忘记密码
            case R.id.activity_login_btn_forgetpsw:
                startActivity(ForgetPswActivity.class);
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        //判断手机号位数
        if (activityLoginEdPhone.getText().length()!=11){
            ToastUtil.showShort(context,"请输入正确的手机号!");
            return;
        }
        //判断是否选择身份
        //licenceIS();

        //判断是否输入密码
        if (activityLoginEdPsw.getText()==null){
            ToastUtil.showShort(context,"请输入密码!");
            return;
        }

        //调用登录接口
        String url;
        //判断使用的身份网址
        if (type.equals("0")){
            url = NetConstantUrl.LOGIN_URL+"&phone="+activityLoginEdPhone.getText().toString()+"&password="+activityLoginEdPsw.getText().toString();
        }else {
            url = NetConstantUrl.LOGIN_URL+"&phone="+activityLoginEdPhone.getText().toString()+"&password="+activityLoginEdPsw.getText().toString()
                    +"&type="+type;
        }
        Log.e("TAG_login",url);
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context,result)){
                    loginReturn = new LoginReturn();
                    loginReturn = getBeanFromJson(result);
                    spInLocal();
                    getDuty();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 根据登录信息获取职务
     */
    private void getDuty() {
        //老师登录-->获取职务
        //家长登录-->获取宝宝的信息
        if(SPUtils.get(context,LocalConstant.USER_TYPE,"").equals("1")){
            String url_duty = NetConstantUrl.GET_DUTY + "&teacherid=" + loginReturn.getId();
            VolleyUtil.VolleyGetRequest(context, url_duty, new VolleyUtil.VolleyJsonCallback() {
                @Override
                public void onSuccess(String result) {
                    if (JsonResult.JSONparser(context,result)){
                        //判断是否为校长
                        checkisPrinsiple(result);
                        //判断是否为班主任
                        checkisClassleader(result);
                        SPUtils.put(context, LocalConstant.USER_IS_PRINSIPLE, isPrinsiple);
                        SPUtils.put(context, LocalConstant.USER_IS_CLASSLEADER, isClassleader);
                        //如果是班主任，获取所任班级信息，否则跳转到主页面
                        if(isClassleader.equals("y")){
                            //获取班主任班级信息
                            getClassInfomation();
                        }else{
                            startActivity(MainActivity.class);
                            finish();
                        }
                    }
                }
                @Override
                public void onError() {

                }
            });

        }else{
            //获取家长对应的宝宝信息，并存入本地
            getBabyInfo();
        }
    }

    /**
     * 获取家长所关联的宝宝信息
     */
    private void getBabyInfo() {
        final String baby_url = NetConstantUrl.GET_USER_RELATION + "&userid=" + loginReturn.getId();
        VolleyUtil.VolleyGetRequest(context, baby_url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                BabyInfo babyInfo = getBabyInfoFromJson(result).get(0);
                SPUtils.put(context,LocalConstant.USER_BABYID,babyInfo.getStudentid());
                SPUtils.put(context,LocalConstant.USER_CLASSID,babyInfo.getClasslist().get(0).getClassid());
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 获取班主任所任班级信息
     */
    private void getClassInfomation() {
        String url_classinfo = NetConstantUrl.GET_CLASSINFO + "&teacherid=" + loginReturn.getId();
        VolleyUtil.VolleyGetRequest(context, url_classinfo, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context,result)){
                    //得到班级信息
                    ClassInfo classInfo = getClassInfoFromJson(result).get(0);
                    //记录班级id到本地
                    SPUtils.put(context, LocalConstant.USER_CLASSID, classInfo.getClassid());
                    startActivity(MainActivity.class);
                    finish();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 判断是否为校长
     * @param result
     * @return
     */
    private void checkisPrinsiple(String result) {
        JSONArray data = null;
        try {
            JSONObject json = new JSONObject(result);
            data = json.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length(); i++) {
            JSONObject itemObject = data.optJSONObject(i);
            if(itemObject.optString("id").equals("1")){
                isPrinsiple = "y";
                return;
            }
        }
        isPrinsiple = "n";
    }

    /**
     * 判断是否为班主任
     * @param result
     * @return
     */
    private void checkisClassleader(String result) {
        JSONArray data = null;
        try {
            JSONObject json = new JSONObject(result);
            data = json.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length(); i++) {
            JSONObject itemObject = data.optJSONObject(i);
            if(itemObject.optString("id").equals("3")){
                isClassleader = "y";
                return;
            }
        }
        isClassleader = "n";
    }


    /**
     * 将需要的信息存储到本地
     */
    private void spInLocal() {
        SPUtils.put(context, LocalConstant.USER_ID,loginReturn.getId());
        SPUtils.put(context,LocalConstant.USER_NAME,loginReturn.getName());
        SPUtils.put(context,LocalConstant.USER_PHOTO,loginReturn.getPhoto());
        SPUtils.put(context, LocalConstant.USER_TYPE, type);
        SPUtils.put(context,LocalConstant.USER_PASSWORD,loginReturn.getPassword());
    }

    /**
     * 判断选择身份登录
     */
    private void licenceIS() {
        loginRgLicence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == loginRbParent.getId()) {//家长身份登录
                    type = "0";
                } else if (checkedId == loginRbTeacher.getId()) {//教师身份登录
                    type = "1";
                }
            }
        });
    }

    /**
     * 字符串转模型（登录信息）
     * @param result
     * @return
     */
    private LoginReturn getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<LoginReturn>() {
        }.getType());
    }

    /**
     * 字符串转模型（班级信息）
     * @param result
     * @return
     */
    private List<ClassInfo> getClassInfoFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<ClassInfo>>() {
        }.getType());
    }

    /**
     * 字符串转模型（宝宝信息）
     * @param     * @return
     */
    private List<BabyInfo> getBabyInfoFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<BabyInfo>>() {
        }.getType());
    }
}
