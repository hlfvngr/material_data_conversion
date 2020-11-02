package com.hgits.hotc.common.config;

import com.hgits.hotc.common.interceptor.SqlStatementInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    /**
     * 配置 sql打印拦截器
     * application.yml中 hgits.showsql: true 时生效
     *
     * @return SqlStatementInterceptor
     */
    @Bean
    @ConditionalOnProperty(name = "hgits.showsql", havingValue = "true")
    SqlStatementInterceptor sqlStatementInterceptor() {
        return new SqlStatementInterceptor();
    }
}
