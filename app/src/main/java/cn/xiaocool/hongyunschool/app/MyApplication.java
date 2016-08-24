package cn.xiaocool.hongyunschool.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


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

        initImageLoder();
    }

    /**
     * 配置Imageloader
     */
    private void initImageLoder() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(), "hyx/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getBaseContext()).memoryCacheExtraOptions(480, 800)
                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1).tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
                .discCacheSize(30 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(1000)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(getBaseContext(), 5 * 1000, 30 * 1000))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
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
