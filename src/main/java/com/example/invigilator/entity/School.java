package com.example.invigilator.entity;

public class School {
    //主键
    private Integer schoolId;
    //学校名称
    private String schoolName;
    //有效性（0.无效 1.有效）
    private Integer valid;
    //备注
    private String remark;

    public School() {
    }

    public School(Integer schoolId, String schoolName, Integer valid, String remark) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.valid = valid;
        this.remark = remark;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", valid=" + valid +
                ", remark='" + remark + '\'' +
                '}';
    }
}
