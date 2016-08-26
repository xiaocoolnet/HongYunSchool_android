package cn.xiaocool.hongyunschool.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
