package com.hgits.hotc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 文件名称(File Name)：App
 * 功能描述(Description)：此模块的功能描述与大概流程说明
 * 数据表(Tables)：本文件中所操作过的数据表
 * 作者(Author)：  zerro
 * 日期(Create Date)：2020/2/24
 * 修改记录(Revision History)：
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan({"com.hgits.hotc.dao.old", "com.hgits.hotc.dao.news"})
public class App extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // 配置静态资源文件路径
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

}