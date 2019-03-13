package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private final static String URL = "http://192.168.43.188:8080/api/mailbox";

    private EditText activity_main_id_et;
    private EditText activity_main_content_et;
    private TextView activity_main_message_tv;

    private String myId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //每次回到主界面时绑定一次帐号，拉起被系统杀死的信鸽服务
        XGPushManager.bindAccount(this, myId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent event) {
        String text = event.getSender()
                + "\n\n留言内容："
                + event.getContent()
                + "\n留言时间："
                + event.getDate();
        activity_main_message_tv.setText(text);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        myId = getIntent().getStringExtra("id");

        activity_main_id_et = findViewById(R.id.activity_main_id_et);
        activity_main_content_et = findViewById(R.id.activity_main_content_et);
        activity_main_message_tv = findViewById(R.id.activity_main_message_tv);

        findViewById(R.id.activity_main_send_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userId = activity_main_id_et.getText().toString();
                        String content = activity_main_content_et.getText().toString();

                        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(content)) {
                            Utils.showToast(MainActivity.this, "对方的ID或内容不能为空");
                        }else {
                            delivery(myId, userId, content);
                        }
                    }
                });
    }

    /**
     * 调用后台接口，把相关数据交给后台，后台再转发给信鸽进行推送给对方。
     * @param sender
     * @param receiver
     * @param content
     */
    private void delivery(String sender, String receiver, String content) {
        OkGo.<String>post(URL)
                .params("sender", sender)
                .params("receiver", receiver)
                .params("content", content)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String str = response.body();
                        if (str.equals("{\"ret_code\":0}")) {
                            Utils.showToast(MainActivity.this, "发送成功");
                            activity_main_content_et.setText("");
                        }else {
                            Utils.showToast(MainActivity.this, "发送失败：" + str);
                        }
                    }
                });
    }
}
