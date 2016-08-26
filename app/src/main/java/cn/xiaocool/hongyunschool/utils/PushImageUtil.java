package cn.xiaocool.hongyunschool.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.xiaocool.hongyunschool.bean.PhotoWithPath;
import cn.xiaocool.hongyunschool.callback.PushImage;
import cn.xiaocool.hongyunschool.net.NetConstantUrl;

/**
 * Created by Administrator on 2016/8/25.
 */
public class PushImageUtil {
    private static final int ADD_KEY = 4;
    private static final int ADD_IMG_KEY1 = 101;
    private static final int ADD_IMG_KEY2 = 102;
    private static final int ADD_IMG_KEY3 = 103;
    private static final int ADD_IMG_KEY4 = 104;
    private static final int ADD_IMG_KEY5 = 105;
    private static final int ADD_IMG_KEY6 = 106;
    private static final int ADD_IMG_KEY7 = 107;
    private static final int ADD_IMG_KEY8 = 108;
    private static final int ADD_IMG_KEY9 = 109;
    private static Context mContext;
    private int imgNums = 0;
    private ArrayList<PhotoWithPath> photoWithPaths;
    private boolean isOk;
    private PushImage pushIamge;

    public void setPushIamge(Context context,ArrayList<PhotoWithPath> p,PushImage pushIamge) {
        this.photoWithPaths = p;
        this.mContext = context;
        this.pushIamge = pushIamge;
        if (photoWithPaths.size()>0){
            pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY1);
        }else {
            pushIamge.error();
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case ADD_IMG_KEY1:
                    if (msg.obj != null) {
                      if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                          imgNums = 1;
                          if (imgNums < photoWithPaths.size()) {
                              pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY2);
                          }else {
                              pushIamge.success(true);
                              isOk = true;
                          }
                      }else {
                          pushIamge.error();
                          Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                      }
                    }
                    break;
                case ADD_IMG_KEY2:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 2;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY3);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY3:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 3;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY4);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY4:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 4;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY5);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY5:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 5;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY6);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY6:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 6;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY7);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY7:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 7;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY8);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY8:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 8;
                            if (imgNums < photoWithPaths.size()) {
                                pushImage(photoWithPaths.get(imgNums),ADD_IMG_KEY9);

                            }else {
                                pushIamge.success(true);
                                isOk = true;
                            }
                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case ADD_IMG_KEY9:
                    if (msg.obj != null) {
                        if (JsonResult.JSONparser(mContext, String.valueOf((JSONObject)msg.obj))){
                            imgNums = 9;
                            isOk = true;
                            pushIamge.success(true);

                        }else {
                            pushIamge.error();
                            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

            }
        }
    };

    private void pushImage(PhotoWithPath photoWithPath, int addImgKey) {
        pushImg(photoWithPath.getPicPath(),addImgKey);
    }

    public void pushImg(final String picPath,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("upfile",picPath));
                String result = NetBaseUtils.getResponseForImg(NetConstantUrl.PUSH_IMAGE, params, mContext);
                try {
                    JSONObject obj = new JSONObject(result);
                    msg.what = KEY;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

}
