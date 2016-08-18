package cn.xiaocool.hongyunschool.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by Administrator on 2016/3/14.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue;
    private static RequestQueue requestQueueFile;
    private static MyApplication myApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        //请求队列
        requestQueue = Volley.newRequestQueue(this);
        myApplication = new MyApplication();

    }





    /**
     * 拿到消息队列
     */
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * 拿到消息队列
     */
    public static RequestQueue getFileRequestQueue() {
        return requestQueueFile;
    }

    /**
     * 拿到消息队列
     */
    public static Application getApplication() {
        return myApplication;
    }



}
