package com.tianma.spring.mvc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置文件
 *
 * Created by Fiboliu on 2014-12-08.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class MyBatisConfig implements ApplicationContextAware {

    ///这个接口可以用于获取spring上下文中定义的所有bean
    @Autowired
    private Environment env;

    private ApplicationContext context;

    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_MAX_ACTIVE = "db.maxActive";

    private static final String DB_URL2 = "db.second.url";
    private static final String DB_USERNAME2 = "db.second.username";
    private static final String DB_PASSWORD2 = "db.second.password";
    private static final String DB_MAX_ACTIVE2 = "db.second.maxActive";


    // linkcloud primary data source
    @Bean(name = "primaryDS")
    @Qualifier("primaryDS")
    @Primary
    public DruidDataSource primaryDS() {

        final String url = Preconditions.checkNotNull(env.getProperty(DB_URL));
        final String username = Preconditions.checkNotNull(env.getProperty(DB_USERNAME));
        final String password = env.getProperty(DB_PASSWORD);
        final int maxActive = Integer.parseInt(env.getProperty(DB_MAX_ACTIVE, "200"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }

    // linkcloud session factory
    @Bean(name = "primaryFactory")
    @Qualifier("primaryFactory")
    public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(primaryDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/**/linkcloud/*.xml"));//配置了mapper的地址
        factoryBean.setConfigLocation(context.getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();//一个sqlsessionfactory就可以操作mapper里面定义的操作了
    }

    // linkcloud transaction manager
    @Bean(name = "primaryManager")
    @Qualifier("primaryManager")
    public DataSourceTransactionManager transactionManagerPrimary() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(primaryDS());
        return transactionManager;//配置事物管理器
    }

    // freelink druid source
    @Bean(name = "secondaryDS")
    @Qualifier("secondaryDS")
    public DruidDataSource secondaryDS() {

        final String url = Preconditions.checkNotNull(env.getProperty(DB_URL2));
        final String username = Preconditions.checkNotNull(env.getProperty(DB_USERNAME2));
        final String password = env.getProperty(DB_PASSWORD2);
        final int maxActive = Integer.parseInt(env.getProperty(DB_MAX_ACTIVE2, "200"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }

    // freelink session factory
    @Bean(name = "secondaryFactory")
    @Qualifier("secondaryFactory")
    public SqlSessionFactory sqlSessionFactorySecondary() throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/**/linkcloud/*.xml"));//包括messageMapper
        factoryBean.setConfigLocation(context.getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    //freelink datasource manager
    @Bean(name = "secondaryManager")
    @Qualifier("secondaryManager")
    public DataSourceTransactionManager transactionManagerSecondary() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(secondaryDS());
        return transactionManager;//配置事物管理器
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}


