package com.example.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        final EditText activity_login_id_et = findViewById(R.id.activity_login_id_et);

        //确认按钮
        findViewById(R.id.activity_login_confirm_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = activity_login_id_et.getText().toString();
                        if (TextUtils.isEmpty(id)) {
                            Utils.showToast(LoginActivity.this, "ID 不能为空");
                        }else {
                            setAccount(id);
                        }
                    }
                });
    }

    /**
     * 信鸽绑定账号注册
     * @param id
     */
    private void setAccount(final String id) {
        String message = "设置ID中，请稍后...";
        Utils.showProgressDialog(this, message, new Utils.OnRunningListener() {
            @Override
            public void onRunning(final ProgressDialog dialog) {
                //id账号绑定信鸽推送服务
                XGPushManager.bindAccount(LoginActivity.this, id, new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object o, int i) {
                        dialog.dismiss();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail(Object o, int i, String s) {
                        dialog.dismiss();
                        Utils.showToast(LoginActivity.this, "错误信息：" + s);
                    }
                });
            }
        });
    }
}
