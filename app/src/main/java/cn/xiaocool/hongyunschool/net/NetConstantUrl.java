package cn.xiaocool.hongyunschool.net;

/**
 * Created by Administrator on 2016/8/24.
 */
public class NetConstantUrl {

    /**
     * 登录
     * 参数&phone=17862816935&password=123&type=1
     * type  1--老师  2--家长
     */
    public static final String LOGIN_URL = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=applogin";
    /**
     * 图片拼接地址
     */
    public static final String IMAGE_URL = "http://hyx.xiaocool.net/uploads/microblog/";
    /**
     * 上传图片地址
     */
    public static final String PUSH_IMAGE = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=WriteMicroblog_upload";
    /**
     * 发布学校信息
     */
    public static final String SEND_SCHOOL_NEWS = "http://hyx.xiaocool.net/index.php?g=apps&m=message&"+"a=send_message";
    /**
     * 发布动态
     */
    public static final String SEND_TREND = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=WriteMicroblog";
}
