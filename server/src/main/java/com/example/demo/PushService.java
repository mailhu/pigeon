package com.example.demo;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class PushService {

    //填写你信鸽提供给你的accessId
    private final static long ACCESS_ID = 0;

    //填写你信鸽提供给你的secretKey
    private final static String SECRET_KEY = "";

    /**
     * 调用信鸽后台API，让信鸽把消息推送到移动端。
     * @param sender
     * @param receiver
     * @param content
     */
    public static String push(String sender, String receiver, String content) {
        //设置消息
        Message message = new Message();
        //设置推送消息的类型
        message.setType(Message.TYPE_MESSAGE);
        //设置推送消息的标题。
        message.setTitle("来自 " + sender + " 的留言");
        //创建Map对象
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);    //消息的内容
        map.put("date", new Date());    //该条消息的时间
        message.setCustom(map);         //设置自定义的key-value

        //推送消息
        XingeApp xingeApp = new XingeApp(ACCESS_ID, SECRET_KEY);
        //调用推送消息给单个账号的方法
        //参数一填0即可，参数二为推送目标账号，参数三为消息内容
        //返回值为推送结果
        JSONObject result = xingeApp.pushSingleAccount(0, receiver, message);

        return String.valueOf(result);
    }
}
