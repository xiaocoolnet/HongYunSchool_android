package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;

public class ForgetPswActivity extends BaseActivity {
    @BindView(R.id.activity_forget_psw_ed_phone)
    EditText activityForgetPswEdPhone;
    @BindView(R.id.activity_forget_psw_btn_getcode)
    TextView activityForgetPswBtnGetcode;
    @BindView(R.id.activity_forget_psw_ed_code)
    EditText activityForgetPswEdCode;
    @BindView(R.id.activity_forget_psw_ed_psw)
    EditText activityForgetPswEdPsw;
    @BindView(R.id.activity_forget_psw_cb_psw)
    CheckBox activityForgetPswCbPsw;
    @BindView(R.id.activity_forget_psw_ed_confirmpsw)
    EditText activityForgetPswEdConfirmpsw;
    @BindView(R.id.activity_forget_psw_cb_confirmpsw)
    CheckBox activityForgetPswCbConfirmpsw;
    @BindView(R.id.activity_forget_psw_btn_finish)
    TextView activityForgetPswBtnFinish;
    private Context context;

    private int time = 30;

    private Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (time == 0){
                activityForgetPswBtnGetcode.setText("获取验证码");
                activityForgetPswBtnGetcode.setFocusable(true);
                activityForgetPswBtnGetcode.setFocusableInTouchMode(true);
                activityForgetPswBtnGetcode.setClickable(true);
                time = 30;
            }else {
                activityForgetPswBtnGetcode.setClickable(false);
                activityForgetPswBtnGetcode.setText(time-- + "秒重发");
                timeHandler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        setTopName("修改密码");
        context = this;
    }

    @Override
    public void requsetData() {

    }

    @OnClick({R.id.activity_forget_psw_btn_getcode, R.id.activity_forget_psw_cb_psw, R.id.activity_forget_psw_cb_confirmpsw, R.id.activity_forget_psw_btn_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.activity_forget_psw_btn_getcode:
                getVerification();
                break;
            //切换密码可见性
            case R.id.activity_forget_psw_cb_psw:
                changePswVisibility(activityForgetPswCbPsw,activityForgetPswEdPsw);
                break;
            //切换确认密码可见性
            case R.id.activity_forget_psw_cb_confirmpsw:
                changePswVisibility(activityForgetPswCbConfirmpsw,activityForgetPswEdConfirmpsw);
                break;
            //完成
            case R.id.activity_forget_psw_btn_finish:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerification() {
        String telephone = activityForgetPswEdPhone.getText().toString().trim();
        //检查手机号码是否合理
        if (!isMobile(telephone)) {
            Toast.makeText(context, "手机号码有误，请检查！", Toast.LENGTH_SHORT).show();
            return;
        }
        timeHandler.sendEmptyMessageDelayed(1, 100);
    }

    /**
     * 校验电话号码
     *
     * @param str
     * @return 是否合法的电话号码
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 切换密码可见性
     * @param checkBox
     * @param editText
     */
    public void changePswVisibility(CheckBox checkBox,EditText editText){
        if(checkBox.isChecked()){
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editText.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = editText.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
}
