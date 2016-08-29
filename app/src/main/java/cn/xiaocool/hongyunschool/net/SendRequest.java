package cn.xiaocool.hongyunschool.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
    public void send_newsgroup(final String send_user_id,final String send_user_name,final String message_content, final String receiver_user_id, final String picture_url, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picture_url.equals("null")) {
                    data = "&send_user_id=" + send_user_id + "&schoolid=" + 1 + "&send_user_name=" + send_user_name
                            + "&message_content=" + message_content + "&receiver_user_id=" + receiver_user_id;
                } else {
                    data = "&send_user_id=" + send_user_id + "&schoolid=" + 1 + "&send_user_name=" + send_user_name
                            + "&message_content=" + message_content + "&receiver_user_id=" + receiver_user_id + "&picture_url=" + picture_url;
                }
                Log.e("send_school_news",NetConstantUrl.SEND_SCHOOL_NEWS + data);
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
    //http://wxt.xiaocool.net/index.php?g=apps&m=index&a=WriteMicroblog&userid=681&schoolid=1&type=1&content=8-4成长日记&classid=1&picurl=newsgroup6241470274805597.jpg,newsgroup7011470274805547.jpg

    /**
     * 发布动态
     * @param userid
     * @param schoolid
     * @param type
     * @param content
     * @param classid
     * @param picurl
     * @param KEY
     */
    public void send_trend(final String userid, final String schoolid, final String type, final String classid, final String content, final String picurl, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picurl.equals("null")) {
                    data = "&userid=" + 681 + "&schoolid=" + 1 + "&type=" + "1"
                            + "&content=" + content + "&classid=" + "1";
                } else {
                    data = "&userid=" + 681 + "&schoolid=" + 1 + "&type=" + "1"
                            + "&content=" + content + "&classid=" + "1" + "&picurl=" + picurl;
                }
                String result_data = NetUtil.getResponse(NetConstantUrl.SEND_TREND, data);
                Log.e("send_trend-----",result_data);
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
