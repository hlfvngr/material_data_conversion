package com.hgits.hotc.entity.enums;

public enum Summation {

    SQUAD(1, "squadsum"),
    NATURAL(2, "naturalsum");

    private int code;

    private String desc;

    Summation(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
