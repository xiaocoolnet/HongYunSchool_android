package cn.xiaocool.hongyunschool.net;

/**
 * Created by Administrator on 2016/8/24.
 */
public class NetConstantUrl {

    /**
     * 获取验证码
     * &phone
     *
     */
    public static final String GET_PHONE_CODE = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=SendMobileCode";

    /**
     * 判断验证码是否正确
     * &phone
     * &code
     */
    public static final String PHONE_CODE_ISOK = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=UserVerify";
    /**
     * 忘记密码--设置密码
     * phone
     * pass
     */
    public static final String FORGET_SET_PSW = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=UpadataPass_Activate";
    /**
     * 修改密码
     * userid
     * pass
     */
    public static final String RESET_PSW = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=forgetPass_Activate";
    /**
     * 登录
     * 参数&phone=17862816935&password=123&type=1
     * type  1--老师  2--家长
     */
    public static final String LOGIN_URL = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=applogin";
    /**
     * 图片拼接地址
     */
    public static final String IMAGE_URL = "http://hyx.xiaocool.net/data/upload/";
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
     * http://hyx.xiaocool.net/index.php?g=apps&m=index&a=WriteMicroblog_else&type=1
     * &userid=9188&content=%E6%A0%A1%E9%85%B7&schoolid=8&classid=91,92&picurl=1.jpg,2.jpg
     */
    public static final String SEND_TREND = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=WriteMicroblog_else";
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
    public static final String GET_SCHOOL_CLASS = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getclasslist&schoolid=";

    /**
     * 根据班级id获取班级学生
     */
    public static final String GET_CLASS_BYID = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getStudentlistByClassid";

    /**
     * 获取全校科室及科室下的老师
     */
    public static final String GET_SCHOOL_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getteacherinfo&schoolid=";

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
    public static final String GET_CLASS_NEWS_ALL = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=GetAllClassInfo&schoolid=";

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
    public static final String GET_WEB_SYSTEM_NEWS = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=getSystemMessages&term_id=3";
    /**
     * 校网H5拼接地址
     */
    public final static String WEB_LINK = "http://hyx.xiaocool.net/index.php?g=portal&m=article";
    /**
     * 校网图片拼接地址
     */
    public final static String WEB_IMAGE_URL = "http://hyx.xiaocool.net";
    /**
     * 系统消息拼接地址
     */
    public final static String SYSTEM_H5 = "http://hyx.xiaocool.net/index.php?g=portal&m=article&a=system&id=";

    /**
     * 获取个人信息
     */
    public static final String GET_USER_INFO = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetUsers";

    /**
     * 获取全校班级和老师
     */
    public static final String GET_CLASS_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getclassteacherlist&schoolid=";

    /**
     * 获取全校班级和家长
     */
    public static final String GET_PARENT_ALL = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=GetSchoolParent&schoolid=";

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
    public static final String GET_TRENDS_PARENT = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetMicroblog&type=1&schoolid=";

    /**
     * 获取总积分
     */
    public static final String GET_INTEGRATION_TOTAL = "http://hyx.xiaocool.net//index.php?g=apps&m=index&a=GetIntegralList&userid=";

    /**
     * 获取积分列表
     */
    public static final String GET_INTEGRATION_LIST = "http://hyx.xiaocool.net//index.php?g=apps&m=index&a=GetIntegralInfo&userid=";

    /**
     * 获取家长信箱（家长）
     */
    public static final String GET_FEEDBACK_PARENT = "http://hyx.xiaocool.net/index.php?g=apps&m=student&a=ParentGetParentMessage&userid=";

    /**
     * 获取家长信箱（老师有回复的）
     */
    public static final String GET_FEEDBACK_BE = "http://hyx.xiaocool.net/index.php?g=apps&m=student&a=GetParentMessageBe&schoolid=";
    /**
     * 获取家长信箱（老师无回复的）
     */
    public static final String GET_FEEDBACK_NULL = "http://hyx.xiaocool.net/index.php?g=apps&m=student&a=GetParentMessageNull&schoolid=";

    /**
     * 获取教师群发的短信（班主任）
     */
    public static final String GET_SHORT_MESSAGE = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetMobileMessage&userid=";

    /**
     * 五公开专栏（作业公告）
     */
    public static final String FIVE_PUBLIC_HOMEWORK = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=gethomeworknotice&schoolid=";

    /**
     * 五公开专栏（校本课程）
     */
    public static final String FIVE_PUBLIC_SUBJECT1 = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getschoolsyl&schoolid=";

    /**
     * 五公开专栏（校本选修课程）
     */
    public static final String FIVE_PUBLIC_SUBJECT2 = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getschoolsylchoice&schoolid=";

    /**
     * 五公开专栏（课时）
     */
    public static final String FIVE_PUBLIC_SUBJECT_TIME = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getclasstime&schoolid=";

    /**
     * 五公开专栏（期末检测）
     */
    public static final String FIVE_PUBLIC_END = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getendtest&schoolid=";

    /**
     * 五公开专栏（节假日）
     */
    public static final String FIVE_PUBLIC_HOLIDAY = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=getholiday&schoolid=";

    /**
     * 获取在线留言
     */
    public static final String GET_ONLINE_COMMENT = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetServiceFeed&userid=";
    /**
     * 获取家长所在班级的老师
     */
    public static final String GET_MTCLASS_TEACHER = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=getTeacher&classid=";

    /**
     * 检查链接
     */
    public static final String CHECK_VERSION = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=CheckVersion&type=android&versionid=";

    /**
     * 获取轮播图
     */
    public static final String GET_SLIDER_URL = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=GetRotatePic&schoolid=";

    /**
     * 获取老师对应的班级
     */
    public static final String TC_GET_CLASS = "http://hyx.xiaocool.net/index.php?g=apps&m=teacher&a=TeacherGetClass";

    /**
     * 学校消息:
     */
    public static final String SCHOOL_MNEWS_DELET = "http://hyx.xiaocool.net/index.php?g=apps&m=message&a=delete&id=";

    /**
     * 班级消息
     */
    public static final String CLASS_NEWS_DELET = "http://hyx.xiaocool.net/index.php?g=apps&m=student&a=delete_homework&id=";

     /**
      * 学校公告
      */
    public static final String SCHOOL_ANNOUNCE_DELET = "http://hyx.xiaocool.net/index.php?g=apps&m=school&a=delete_notice&id=";

    /**
     * 动态
     */
    public static final String TREND_DELET = "http://hyx.xiaocool.net/index.php?g=apps&m=index&a=delete_mic&id=";

}
