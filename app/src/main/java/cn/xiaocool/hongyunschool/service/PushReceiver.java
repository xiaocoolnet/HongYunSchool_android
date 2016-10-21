package cn.xiaocool.hongyunschool.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import cn.xiaocool.hongyunschool.activity.ClassNewsActivity;
import cn.xiaocool.hongyunschool.activity.MainActivity;
import cn.xiaocool.hongyunschool.activity.SchoolAnnounceActivity;
import cn.xiaocool.hongyunschool.activity.SchoolNewsActivity;
import cn.xiaocool.hongyunschool.net.LocalConstant;
import cn.xiaocool.hongyunschool.utils.SPUtils;


/**
 * Created by Administrator on 2016/9/8.
 */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
    private static final String JPUSHTRUST = "JPUSHTRUST";
    private static final String JPUSHNOTICE = "JPUSHNOTICE";
    private static final String JPUSHBACKLOG = "JPUSHBACKLOG";
    private static final String JPUSHDAIJIE = "JPUSHDAIJIE";
    private static final String JPUSHLEAVE = "JPUSHLEAVE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";
//    private static final String JPUSHMESSAGE = "JPUSHMESSAGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String type = bundle.getString(JPushInterface.EXTRA_EXTRA);

        if (type==null)return;
        String str = "";
        try {
            JSONObject jsonObject = new JSONObject(type);
            str = jsonObject.getString("type");
        } catch (JSONException e) {
            Log.i(TAG, "JSONException" + type);
            e.printStackTrace();
        }
        Log.i(TAG, "[PushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));


//        //本地存储条数
//        int msgNum = (int) SPUtils.get(context, JPUSHMESSAGE, 0);
//        int trustNum = (int) SPUtils.get(context, JPUSHTRUST, 0);
//        int noticeNum = (int) SPUtils.get(context, JPUSHNOTICE, 0);
//        int backlogNum = (int)SPUtils.get(context, JPUSHBACKLOG,0);
//        int daijieNum = (int) SPUtils.get(context, JPUSHDAIJIE, 0);
//        int leaveNum = (int) SPUtils.get(context, JPUSHLEAVE, 0);
//
//
//        if (str.equals("message")){//信息群发
//            msgNum = msgNum + 1;
//            SPUtils.put(context,JPUSHMESSAGE,msgNum);
//        }else if (str.equals("trust")){//家长叮嘱
//            trustNum = trustNum + 1;
//            SPUtils.put(context,JPUSHTRUST,trustNum);
//        }else if (str.equals("notice")){//通知公告
//            noticeNum = noticeNum + 1;
//            SPUtils.put(context,JPUSHNOTICE,noticeNum);
//        }else if (str.equals("schedule")){//待办事项
//            backlogNum = backlogNum + 1;
//            SPUtils.put(context,JPUSHBACKLOG,backlogNum);
//        }else if (str.equals("delivery")){//待解确认
//            daijieNum = daijieNum + 1;
//            SPUtils.put(context,JPUSHDAIJIE,daijieNum);
//        }else if (str.equals("homework")){//家庭作业
//
//        }else if (str.equals("leave")){//在线请假
//            leaveNum = leaveNum + 1;
//            SPUtils.put(context,JPUSHLEAVE,leaveNum);
//        }else if (str.equals("activity")){//班级活动
//
//        }else if (str.equals("comment")){//教师点评
//
//        }
//
//        Intent pointIntent = new Intent();
//        pointIntent.setAction("com.USER_ACTION");
//        context.sendBroadcast(pointIntent);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.i(TAG, "[PushReceiver] 接收Registeration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "[PushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        }else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "[PushReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.i(TAG, "[PushReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.i(TAG, "[PushReceiver] 用户点击打开了通知");


                if (str.equals("message")) {//学校消息
                    if (isTeacher(context)){
                        Intent i = new Intent(context, SchoolNewsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }else {
                        Intent i = new Intent(context, MainActivity.class);
                        i.putExtra("isParent",true);
                        i.putExtra("pushtype","school");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }

                }else if (str.equals("notice")){//学校通知
                    if (isTeacher(context)){
                        Intent i = new Intent(context, SchoolAnnounceActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }else {
                        Intent i = new Intent(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }

                }else if (str.equals("classinfo")){//班级消息
                    if (isTeacher(context)){
                        Intent i = new Intent(context, ClassNewsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }else {
                        Intent i = new Intent(context, MainActivity.class);
                        i.putExtra("isParent",true);
                        i.putExtra("pushtype","class");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        context.startActivity(i);
                    }

                }else{
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    context.startActivity(i);
                }



        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.i(TAG, "[PushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.i(TAG, "[PushReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.i(TAG, "[PushReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private boolean isTeacher(Context context) {
        if (SPUtils.get(context, LocalConstant.USER_TYPE,"").equals("1")){
            return true;
        }
        return false;
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            }
            else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


}
