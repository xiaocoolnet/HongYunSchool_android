package cn.xiaocool.hongyunschool.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 潘 on 2016/3/31.
 */
public class SendRequest {
    private Context mContext;
    private Handler handler;


    public SendRequest(Context context, Handler handler) {
        super();
        this.mContext = context;
        this.handler = handler;

    }



    /**
     * 发送信息群发
     * 接口名称用户发送消息(post传输) <>
     * 接口地址：a=send_message <>
     * 入参：send_user_id,schoolid,send_user_name,message_content,receiver_user_id,picture_url <>
     * 出参：无 <>
     * Demo:http://wxt.xiaocool.net/index.php?g=apps&m=message&a=send_message&send_user_id=600&schoolid=1&send_user_name=呵呵
     * &message_content=222&receiver_user_id=597,600&picture_url=ajhsdiaho.png<>
     */
    public void send_newsgroup(final String message_content, final String receiver_user_id, final String picture_url, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picture_url.equals("null")) {
                    data = "&send_user_id=" + 605 + "&schoolid=" + 1 + "&send_user_name=" + "HHH"
                            + "&message_content=" + message_content + "&receiver_user_id=" + receiver_user_id;
                } else {
                    data = "&send_user_id=" + 605 + "&schoolid=" + 1 + "&send_user_name=" + "HHH"
                            + "&message_content=" + message_content + "&receiver_user_id=" + receiver_user_id + "&picture_url=" + picture_url;
                }

                String result_data = NetUtil.getResponse(NetConstantUrl.SEND_SCHOOL_NEWS, data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = KEY;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }



}
