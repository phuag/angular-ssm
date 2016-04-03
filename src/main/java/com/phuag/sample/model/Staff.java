package com.phuag.sample.model;

public class Staff {
    private Integer staffId;

    private String staffName;

    private String staffSex;

    private Byte staffAge;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex == null ? null : staffSex.trim();
    }

    public Byte getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(Byte staffAge) {
        this.staffAge = staffAge;
    }
}