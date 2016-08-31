package cn.xiaocool.hongyunschool.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class SchoolAnnouncement {

    /**
     * id : 3
     * userid : 599
     * title : 标题
     * content : 内容
     * type : 0
     * create_time : 1472609603
     * username : 校长
     * avatar : weixiaotong.png
     * receive_list : [{"name":"王志恒","photo":"weixiaotong.png","phone":"17862816935","id":"3","noticeid":"3","receiverid":"604","receivertype":"0","create_time":"0"}]
     * pic : [{"id":"5","pictureurl":"hyx8221472460032673.jpg","create_time":"0"},{"id":"6","pictureurl":"hyx9381472460032660.jpg","create_time":"0"}]
     * comment : []
     * like : []
     */

    private String id;
    private String userid;
    private String title;
    private String content;
    private String type;
    private String create_time;
    private String username;
    private String avatar;
    /**
     * name : 王志恒
     * photo : weixiaotong.png
     * phone : 17862816935
     * id : 3
     * noticeid : 3
     * receiverid : 604
     * receivertype : 0
     * create_time : 0
     */

    private List<ReceiveListBean> receive_list;
    /**
     * id : 5
     * pictureurl : hyx8221472460032673.jpg
     * create_time : 0
     */

    private List<PicBean> pic;
    private List<?> comment;
    private List<?> like;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ReceiveListBean> getReceive_list() {
        return receive_list;
    }

    public void setReceive_list(List<ReceiveListBean> receive_list) {
        this.receive_list = receive_list;
    }

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public List<?> getComment() {
        return comment;
    }

    public void setComment(List<?> comment) {
        this.comment = comment;
    }

    public List<?> getLike() {
        return like;
    }

    public void setLike(List<?> like) {
        this.like = like;
    }

    public static class ReceiveListBean implements Serializable {
        private String name;
        private String photo;
        private String phone;
        private String id;
        private String noticeid;
        private String receiverid;
        private String receivertype;
        private String create_time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(String noticeid) {
            this.noticeid = noticeid;
        }

        public String getReceiverid() {
            return receiverid;
        }

        public void setReceiverid(String receiverid) {
            this.receiverid = receiverid;
        }

        public String getReceivertype() {
            return receivertype;
        }

        public void setReceivertype(String receivertype) {
            this.receivertype = receivertype;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

    public static class PicBean {
        private String id;
        private String pictureurl;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPictureurl() {
            return pictureurl;
        }

        public void setPictureurl(String pictureurl) {
            this.pictureurl = pictureurl;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
