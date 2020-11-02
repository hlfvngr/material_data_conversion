package com.hgits.hotc.entity.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Accessors(chain = true)
public class ApiResult implements Serializable {

    private static final long serialVersionUID = -5950791568769781380L;

    private Integer code;

    private String msg;

    private Object data;

}
