package com.hgits.hotc.entity.enums;

public enum ImportResult {

    SUCCESS(0),
    FAIL(1);

    private int code;

    ImportResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
