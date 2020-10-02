package com.example.invigilator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Role {
    //主键
    private Integer roleId;
    //角色名
    private String roleName;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //创建人
    private String createName;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    //更新人
    private String updateName;
    //有效性（0.无效 1.有效）
    private Integer valid;
    //备注
    private String remark;

    public Role() {
    }

    public Role(Integer roleId, String roleName, Date createTime, String createName, Date updateTime, String updateName, Integer valid, String remark) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createTime = createTime;
        this.createName = createName;
        this.updateTime = updateTime;
        this.updateName = updateName;
        this.valid = valid;
        this.remark = remark;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", createName='" + createName + '\'' +
                ", updateTime=" + updateTime +
                ", updateName='" + updateName + '\'' +
                ", valid=" + valid +
                ", remark='" + remark + '\'' +
                '}';
    }
}
