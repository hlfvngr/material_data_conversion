package com.hgits.hotc.common.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DataSourceAop {

    @Before("execution(* com.hgits.hotc.service.impl.old.Old*Impl.*(..))")
    public void setDataSource2Master() {
        log.info("******************开始日志相关记录*******************");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MASTER);
    }

    @After("execution(* com.hgits.hotc.service.impl.old.Old*Impl.*(..))")
    public void removeDataSource2Master() {
        log.info("******************结束日志相关记录*******************");
        DataSourceType.clearDataBaseType();
    }

    @Before("execution(* com.hgits.hotc.service.impl.news.*Impl.*(..))")
    public void setDataSource2Slave() {
        log.info("******************开始插入流水记录*******************");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.SLAVE);
    }

    @After("execution(* com.hgits.hotc.service.impl.news.*Impl.*(..))")
    public void removeDataSource2Slave() {
        log.info("******************结束插入流水记录*******************");
        DataSourceType.clearDataBaseType();
    }
}
