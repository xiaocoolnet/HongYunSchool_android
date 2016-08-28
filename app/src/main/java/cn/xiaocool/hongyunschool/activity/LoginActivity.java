package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.bean.LoginReturn;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;
import cn.xiaocool.hongyunschool.net.VolleyUtil;
import cn.xiaocool.hongyunschool.utils.BaseActivity;
import cn.xiaocool.hongyunschool.utils.JsonResult;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTopName("泓云校");
        hideLeft();
        loginRbParent.setChecked(true);
        context = this;
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
        licenceIS();

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
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context,result)){
                    loginReturn = new LoginReturn();
                    loginReturn = getBeanFromJson(result);
                    spInLocal();
                    startActivity(MainActivity.class);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 将需要的信息存储到本地
     */
    private void spInLocal() {
        SPUtils.put(context, LocalConstant.USER_ID,loginReturn.getId());
        SPUtils.put(context,LocalConstant.USER_NAME,loginReturn.getName());
        SPUtils.put(context,LocalConstant.USER_PHOTO,loginReturn.getPhoto());
        SPUtils.put(context, LocalConstant.USER_TYPE, type);
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
     * 字符串转模型
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
}
