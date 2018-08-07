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
@TableName("demo_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("user_code")
    private String userCode;
    @TableField("role_code")
    private String roleCode;
    @TableField("role_name")
    private String roleName;


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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", roleCode=" + roleCode +
        ", roleName=" + roleName +
        "}";
    }
}
