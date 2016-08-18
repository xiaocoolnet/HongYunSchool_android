package cn.xiaocool.hongyunschool.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.BaseActivity;


public class TESActivity extends BaseActivity {

    @BindView(R.id.ddd)
    TextView ddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);
        ButterKnife.bind(this);

        setRightText("发布").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TESActivity.this, "dsds", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void requsetData() {

    }


    @OnClick(R.id.ddd)
    public void onClick() {
        startActivity(Main2Activity.class);
    }

}
