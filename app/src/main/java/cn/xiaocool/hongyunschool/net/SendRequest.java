package cn.xiaocool.hongyunschool.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.xiaocool.hongyunschool.utils.LogUtils;

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
    public void send_newsgroup(final String send_user_id,final String schoolid,final String message_content,final String sendName, final String receiver_user_id, final String picture_url, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picture_url.equals("null")) {
                    data = "&send_user_id=" + send_user_id + "&schoolid=" + schoolid + "&send_user_name=" + sendName
                            + "&message_content=" + message_content + "&receiver_user_id=" + receiver_user_id;
                } else {
                    data = "&send_user_id=" + send_user_id + "&schoolid=" + schoolid + "&send_user_name=" + sendName
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
     * @param content
     * @param classid
     * @param picurl
     * @param KEY
     */
    public void send_trend(final String userid, final String schoolid, final String classid, final String content, final String picurl, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picurl.equals("null")) {
                    data = "&userid=" + userid + "&schoolid=" + schoolid + "&type=" + "1"
                            + "&content=" + content + "&classid=" + classid;
                } else {
                    data = "&userid=" + userid + "&schoolid=" + schoolid + "&type=" + "1"
                            + "&content=" + content + "&classid=" + classid + "&picurl=" + picurl;
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
    //http://hyx.xiaocool.net/index.php?g=apps&m=school&a=publishnotice&userid=599&title=%E6%A0%87%E9%A2%98&content=%E5%86%85%E5%AE%B9&photo=hyx8221472460032673.jpg,hyx9381472460032660.jpg&reciveid=604
    /**
     * 发布校内通知
     * @param userid
     * @param title
     * @param content
     * @param photo
     * @param reciveid
     * @param KEY
     */
    public void send_school_announce(final String userid, final String title, final String content, final String photo, final String reciveid,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (photo.equals("null")) {
                    data = "&userid=" + userid + "&title=" + title + "&content=" + content
                            + "&reciveid=" + reciveid ;
                } else {
                    data = "&userid=" + userid + "&title=" + title + "&content=" + content
                            + "&reciveid=" + reciveid + "&photo=" + photo ;
                }
                String result_data = NetUtil.getResponse(NetConstantUrl.SEND_ANNOUNCEMENT, data);
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

    //http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=addhomework&schoolid=1&teacherid=599&title=%E5%91%A8%E5%9B%9B%E4%BD%9C%E4%B8%9A&content=%E4%BD%9C%E4%B8%9A%E5%86%85%E5%AE%B9%EF%BC%8C%E5%BF%AB%E6%9D%A5%E7%9C%8B&receiverid=654&picture_url=hyx8221472460032673.jpg,hyx9381472460032660.jpg
    public void send_class_new(final String schoolid, final String teacherid, final String title, final String content, final String receiverid,final String picture_url,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {

                String data = "";
                if (picture_url.equals("null")) {
                    data = "&schoolid=" + schoolid + "&teacherid=" + teacherid + "&title=" + title
                            + "&content=" + content + "&receiverid=" + receiverid ;
                } else {
                    data = "&schoolid=" + schoolid + "&teacherid=" + teacherid + "&title=" + title
                            + "&content=" + content + "&receiverid=" + receiverid + "&picture_url=" + picture_url;
                }
                String result_data = NetUtil.getResponse(NetConstantUrl.SEND_CLASS_NEW, data);
                Log.e("send_class_new-----",result_data);
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

    /**
     * 修改性别
     */
    public void updateSex(final String userid,final int sex, final int key) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + userid + "&sex=" + sex;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=index&a=UpdateUserSex", data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = key;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * 更新名字
     */
    public void updateName(final String userid,final String name, final int key) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + userid + "&nicename=" + name;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=index&a=UpdateUserName", data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = key;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * 更新头像
     */
    public void updateHeadImg(final String userid,final String avatar, final int key) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + userid + "&avatar=" + avatar;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=index&a=UpdateUserAvatar", data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = key;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    public void send_remark(final String id,final String userid, final String content, final String type,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + userid + "&id=" + id + "&content=" + content + "&type=" + type;
                Log.d("final String id", data);
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=school&a=SetComment", data);
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

    /**
     * 点赞
     * @param workBindId
     * @param workPraiseKey
     */
    public void Praise(final String userId,final String workBindId, final int workPraiseKey) {
        LogUtils.d("weixiaotong", "getCircleList");
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                try {
                    String data = "&userid=" + userId + "&id=" + workBindId + "&type=" + 1;
                    String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=school&a=SetLike", data);
                    JSONObject jsonObject = new JSONObject(result_data);
                    msg.what = workPraiseKey;
                    msg.obj = jsonObject;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * 取消点赞
     * @param id
     * @param KEY
     */
    public void DelPraise(final String userid,final String id, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();
            public void run() {
                try {
                    String data = "userid=" + userid + "&id=" + id + "&type=" + "1";
                    String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=school&a=ResetLike", data);

                    LogUtils.e("getIndexSlideNewsList", result_data.toString());
                    JSONObject jsonObject = new JSONObject(result_data);
                    msg.what = KEY;
                    msg.obj = jsonObject;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    //http://hyx.xiaocool.net/index.php?g=apps&m=student&a=AddParentMessage&classid=1&schoolid=1&userid=674&content=%E5%86%85%E5%AE%B9

    /**
     * 发送反馈
     * @param userid
     * @param classid
     * @param schoolid
     * @param content
     * @param KEY
     */
    public void send_feedback(final String userid, final String classid, final String schoolid, final String content,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&classid=" + classid + "&schoolid=" + schoolid + "&userid=" + userid
                            + "&content=" + content ;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=student&a=AddParentMessage", data);
                Log.e("send_feedback-----",result_data);
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

    //http://hyx.xiaocool.net/index.php?g=apps&m=student&a=FeedParentMessage&messageid=1&teacherid=599&feedback=%E5%9B%9E%E5%A4%8D%E5%86%85%E5%AE%B9
    public void feedback(final String messageid, final String teacherid, final String feedback,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&messageid=" + messageid + "&teacherid=" + teacherid + "&feedback=" + feedback;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=student&a=FeedParentMessage", data);
                Log.e("feedback-----",result_data);
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

    //http://hyx.xiaocool.net/index.php?g=apps&m=index&a=SendMessageInfo&phone=18553546580,13276386385,15653579769&message=%E6%B5%8B%E8%AF%95%E4%B8%80%E4%B8%8B&userid=597
    public void sendGroupMessage(final String phone, final String userid, final String message,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&phone=" + phone + "&userid=" + userid + "&message=" + message;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=index&a=SendMessageInfo", data);
                Log.e("sendGroup-----",result_data);
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
    //http://hyx.xiaocool.net/index.php?g=apps&m=index&a=ServiceOnline&userid=597&schoolid=1&content=%E7%95%99%E8%A8%80
    public void sendFeedback(final String userid, final String schoolid, final String content,final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + userid + "&schoolid=" + schoolid + "&content=" + content;
                String result_data = NetUtil.getResponse("http://hyx.xiaocool.net/index.php?g=apps&m=index&a=ServiceOnline", data);
                Log.e("sendGroup-----",result_data);
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
