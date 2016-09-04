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
    /**
     * 发布校内通知
     */
    public static final String SEND_ANNOUNCEMENT = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=publishnotice";
    /**
     * 获取老师职务
     */
    public static final String GET_DUTY = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=GetTeacherDuty";
    /**
     * 获取老师对应的班级信息
     */
    public static final String GET_CLASSINFO = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=GetLeaderClass";
    /**
     * 获取校长所发送的学校信息
     */
    public static final String GET_SCHOOL_NEWS_SEND = "http://hyx.xiaocool.net/index.php?g=Apps&m=Message&a=user_send_message";
    /**
     * 获取家长所接收的学校信息
     */
    public static final String GET_SCHOOL_NEWS_RECEIVE = "http://hyx.xiaocool.net/index.php?g=Apps&m=Message&a=user_reception_message";

    /**
     * 获取家长所关联的学生
     */
    public static final String GET_USER_RELATION = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetUserRelation";

    /**
     * 获取全校班级
     */
    public static final String GET_SCHOOL_CLASS = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getclasslist&schoolid=1";

    /**
     * 根据班级id获取班级学生
     */
    public static final String GET_CLASS_BYID = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getStudentlistByClassid";

    /**
     * 获取全校科室及科室下的老师
     */
    public static final String GET_SCHOOL_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfo&schoolid=1";

    /**
     * 获取校长发送的校内通知
     */
    public static final String GET_ANNOUNCE_SEND = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getnoticelist";

    /**
     * 获取老师接收的校内通知
     */
    public static final String GET_ANNOUNCE_RECEIVE = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=get_receive_notice";
    /**
     * 发送班级消息
     */
    public static final String SEND_CLASS_NEW = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=addhomework";
    /**
     * 获取班主任发布的班级消息
     */
    public static final String GET_CLASS_NEWS_SEND = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=gethomeworklist";

    /**
     * 获取家长接收的班级消息
     */
    public static final String GET_CLASS_NEWS_RECEIVE = "http://hyx.xiaocool.net/index.php?g=apps&m=student&a=gethomeworkmessage";

    /**
     * 校长获取全校班级消息
     */
    public static final String GET_CLASS_NEWS_ALL = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=GetAllClassInfo&schoolid=1";

    /**
     * 校网学校概况获取列表
     */
    public static final String GET_WEB_SCHOOL_INTROUCE = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getWebSchoolInfos&schoolid=";

    /**
     * 校网教师风采获取列表
     */
    public static final String GET_WEB_SCHOOL_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfos&schoolid=";
    /**
     * 校网学生秀场获取列表
     */
    public static final String GET_WEB_SCHOOL_STUDENT = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getbabyinfos&schoolid=";
    /**
     * 校网精彩活动获取列表
     */
    public static final String GET_WEB_SCHOOL_ACTIVITY = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getInterestclass&schoolid=";
    /**
     * 校网校园公告获取列表
     */
    public static final String GET_WEB_SCHOOL_NOTICE = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getSchoolNotices&schoolid=";
    /**
     * 校网新闻动态获取列表
     */
    public static final String GET_WEB_SCHOOL_NEWS = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getSchoolNews&schoolid=";

    /**
     * 校网系统消息
     */
    public static final String GET_WEB_SYSTEM_NEWS = "http://wxt.xiaocool.net/index.php?g=apps&m=index&a=getSystemMessages&term_id=3";
    /**
     * 校网H5拼接地址
     */
    public final static String WEB_LINK = "http://wxt.xiaocool.net/index.php?g=portal&m=article";

    /**
     * 系统消息拼接地址
     */
    public final static String SYSTEM_H5 = "http://wxt.xiaocool.net/index.php?g=portal&m=article&a=system&id=";

    /**
     * 获取个人信息
     */
    public static final String GET_USER_INFO = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetUsers";

    /**
     * 获取全校班级和老师
     */
    public static final String GET_CLASS_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getclassteacherlist&schoolid=1";

    /**
     * 获取全校班级和家长
     */
    public static final String GET_PARENT_ALL = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=GetSchoolParent&schoolid=1";

    /**
     * 获取老师任教班级和家长
     */
    public static final String GET_PARENT_BYTEACHERID = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=GetTeacherClassStudentParent";

    /**
     * 获取对应班级id的班级和家长
     */
    public static final String GET_PARENT_BYCLASSID = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=GetSchoolParent";

    /**
     * 获取动态（家长）
     */
    public static final String GET_TRENDS_PARENT = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetMicroblog&schoolid=1&type=1";

    /**
     * 获取总积分
     */
    public static final String GET_INTEGRATION_TOTAL = "http://hyx.xiaocool.net//index.php?g=apps&m=index&a=GetIntegralList&userid=";

    /**
     * 获取积分列表
     */
    public static final String GET_INTEGRATION_LIST = "http://hyx.xiaocool.net//index.php?g=apps&m=index&a=GetIntegralInfo&userid=";
}
