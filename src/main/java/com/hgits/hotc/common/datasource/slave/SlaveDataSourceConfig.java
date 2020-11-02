package com.hgits.hotc.common.datasource.slave;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = SlaveDataSourceConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = SlaveDataSourceConfig.SESSION_FACTORY)
public class SlaveDataSourceConfig {

    public static final String SESSION_FACTORY = "slaveSqlSessionFactory";
    //mapper类的包路径
    public static final String MAPPER_PACKAGE = "com.hgits.hotc.dao.news";

    private static final String DATASOURCE_NAME = "slaveDataSource";
    //自定义mapper的xml文件路径​
    private static final String MAPPER_XML_PATH = "classpath:/mapper/news/*Mapper.xml";
    //数据源配置的前缀，必须与application.yml中配置的对应数据源的前缀一致
    private static final String DATASOURCE_PREFIX = "spring.datasource.slave";

    //DataSourceTransactionManager的名称，建议按照数据库的名称+TransactionManager驼峰命名的方式赋值
    //如demo数据库，命名如下
    private static final String TRANSACTION_MANAGER_NAME = "slaveTransactionManager";


    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX)
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getMasterDataSource());
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        return bean.getObject();
    }

    //主数据源添加@Primary注解，其它数据源不能添加
    @Primary
    @Bean(name = TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getMasterDataSource());
    }


}
