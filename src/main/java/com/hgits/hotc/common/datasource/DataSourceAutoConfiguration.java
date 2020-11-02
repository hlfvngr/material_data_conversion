package com.hgits.hotc.common.datasource;

import com.hgits.hotc.common.datasource.master.MasterDataSourceConfig;
import com.hgits.hotc.common.datasource.slave.SlaveDataSourceConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;

/**
 * 配置分布式事务管理
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnBean({
        MasterDataSourceConfig.class,
        SlaveDataSourceConfig.class,
        //新增数据源在这里添加
        //XxxDataSourceConfig.class
})
public class DataSourceAutoConfiguration implements TransactionManagementConfigurer {

    @Resource
    private MasterDataSourceConfig masterDataSourceConfig;

    @Resource
    private SlaveDataSourceConfig slaveDataSourceConfig;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new ChainedTransactionManager(
                masterDataSourceConfig.transactionManager(),
                slaveDataSourceConfig.transactionManager()
                //新增数据源在这里添加
                //xxxDataSourceConfig.xxxTransactionManager()
        );
    }
}
