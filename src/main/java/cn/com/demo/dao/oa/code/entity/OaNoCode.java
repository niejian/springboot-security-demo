package cn.com.demo.dao.oa.code.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-08-06
 */
@TableName("oa_no_code")
public class OaNoCode extends Model<OaNoCode> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码ID
     */
    @TableId(value = "codeid", type = IdType.AUTO)
    private Long codeid;
    /**
     * 提交类型名称
     */
    @TableField("command_name")
    private String commandName;
    /**
     * 提交类型
     */
    @TableField("type_code")
    private String typeCode;
    /**
     * 提交编码类型
     */
    @TableField("command_type")
    private String commandType;
    /**
     * 自增规则
     */
    @TableField("increasse_rule")
    private String increasseRule;
    /**
     * 最后更新时间
     */
    @TableField("last_time")
    private Date lastTime;
    /**
     * 递增序号值
     */
    @TableField("code_num")
    private String codeNum;
    /**
     * 备注
     */
    private String remark;


    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getIncreasseRule() {
        return increasseRule;
    }

    public void setIncreasseRule(String increasseRule) {
        this.increasseRule = increasseRule;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.codeid;
    }

    @Override
    public String toString() {
        return "OaNoCode{" +
        "codeid=" + codeid +
        ", commandName=" + commandName +
        ", typeCode=" + typeCode +
        ", commandType=" + commandType +
        ", increasseRule=" + increasseRule +
        ", lastTime=" + lastTime +
        ", codeNum=" + codeNum +
        ", remark=" + remark +
        "}";
    }
}
