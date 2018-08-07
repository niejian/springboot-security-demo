package cn.com.demo.dao.yun.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author niejian
 * @since 2018-07-24
 */
@TableName("demo_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("user_code")
    private String userCode;
    @TableField("user_name")
    private String userName;
    @TableField("user_pwd")
    private String userPwd;

    public User(String id, String userCode, String userName, String userPwd) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", userName=" + userName +
        ", userPwd=" + userPwd +
        "}";
    }
}
