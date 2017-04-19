package com.tianma.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置文件
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class MyBatisConfig implements ApplicationContextAware {

    ///这个接口可以用于获取spring上下文中定义的所有bean
    @Autowired
    private Environment env;

    private ApplicationContext context;

    /**
     * 数据库配置信息
     */
    private static final String FREE_LINK_DB_URL = "config.db.url";
    private static final String FREE_LINK_DB_USERNAME = "config.db.username";
    private static final String FREE_LINK_DB_PASSWORD = "config.db.password";
    private static final String FREE_LINK_DB_MAX_ACTIVE = "config.db.maxActive";

    @Bean
    public DruidDataSource secondaryDS() {

        final String url = env.getProperty(FREE_LINK_DB_URL);
        final String username = env.getProperty(FREE_LINK_DB_USERNAME);
        final String password = env.getProperty(FREE_LINK_DB_PASSWORD);
        final int maxActive = Integer.parseInt(env.getProperty(FREE_LINK_DB_MAX_ACTIVE, "50"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactorySecondary() throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath:com/tianma/repository/**/*Mapper.xml"));
        factoryBean.setConfigLocation(context.getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManagerSecondary() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(secondaryDS());
        return transactionManager;//配置事物管理器
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}


