package com.hgits.hotc.common.exception;

import com.hgits.hotc.entity.vo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e){
        log.error("捕获异常：" + e.getMessage());
        e.printStackTrace();
        return ApiResult.builder().code(1).msg("入库失败,请重试！").build();
    }

}
