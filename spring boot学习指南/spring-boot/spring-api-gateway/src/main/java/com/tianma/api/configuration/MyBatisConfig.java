package com.tianma.api.configuration;

/**
 * Created by fibo on 16-2-12.
 */
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * Created by Silence on 2014-12-08.
 */
@Configuration
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
    private static final String DB_URL_2 = "db.url.2";
    private static final String DB_USERNAME_2 = "db.username.2";
    private static final String DB_PASSWORD_2 = "db.password.2";
    private static final String DB_MAX_ACTIVE_2 = "db.maxActive.2";
    private static final String DB_URL_3 = "db.url.3";
    private static final String DB_USERNAME_3 = "db.username.3";
    private static final String DB_PASSWORD_3 = "db.password.3";
    private static final String DB_MAX_ACTIVE_3 = "db.maxActive.3";
    private static final String DB_URL_4 = "db.url.4";
    private static final String DB_USERNAME_4 = "db.username.4";
    private static final String DB_PASSWORD_4 = "db.password.4";
    private static final String DB_MAX_ACTIVE_4 = "db.maxActive.4";


    @Bean(name = "primaryDS") @Qualifier("primaryDS")
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
    }//for user

    @Bean(name = "secondaryDS") @Qualifier("secondaryDS")
    public DruidDataSource secondaryDS() {
        final String url = Preconditions.checkNotNull(env.getProperty(DB_URL_2));
        final String username = Preconditions.checkNotNull(env.getProperty(DB_USERNAME_2));
        final String password = env.getProperty(DB_PASSWORD_2);
        final int maxActive = Integer.parseInt(env.getProperty(DB_MAX_ACTIVE_2, "200"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }//for client

    @Bean(name = "thirdDS") @Qualifier("thirdDS")
    public DruidDataSource thirdDS() {
        final String url = Preconditions.checkNotNull(env.getProperty(DB_URL_3));
        final String username = Preconditions.checkNotNull(env.getProperty(DB_USERNAME_3));
        final String password = env.getProperty(DB_PASSWORD_3);
        final int maxActive = Integer.parseInt(env.getProperty(DB_MAX_ACTIVE_3, "200"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }//for freelink_config

    @Bean(name = "fourthDS") @Qualifier("fourthDS")
    public DruidDataSource fourthDS() {
        final String url = Preconditions.checkNotNull(env.getProperty(DB_URL_4));
        final String username = Preconditions.checkNotNull(env.getProperty(DB_USERNAME_4));
        final String password = env.getProperty(DB_PASSWORD_4);
        final int maxActive = Integer.parseInt(env.getProperty(DB_MAX_ACTIVE_4, "200"));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }//for freelink_config






    @Bean(name = "primaryFactory")
    @Qualifier("primaryFactory")
    public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(primaryDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/**/UserMapper.xml"));//配置了mapper的地址
        return factoryBean.getObject();//一个sqlsessionfactory就可以操作mapper里面定义的操作了
    }

    @Bean(name = "secondaryFactory")
    @Qualifier("secondaryFactory")
    public SqlSessionFactory sqlSessionFactorySecondary() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/**/Client**.xml"));//包括messageMapper
        return factoryBean.getObject();//一个sqlsessionfactory就可以操作mapper里面定义的操作了
    }


    @Bean(name = "thirdFactory")
    @Qualifier("thirdFactory")
    public SqlSessionFactory sqlSessionFactoryThird() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(thirdDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/**/UserMapper.xml"));//配置了mapper的地址
        return factoryBean.getObject();//一个sqlsessionfactory就可以操作mapper里面定义的操作了
    }

    @Bean(name = "fourthFactory")
    @Qualifier("fourthFactory")
    public SqlSessionFactory sqlSessionFactoryFourth() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(fourthDS());//使用之前的datasource
        factoryBean.setMapperLocations(context.getResources("classpath*:mappers/apilogs/**.xml"));//配置了mapper的地址
        return factoryBean.getObject();//一个sqlsessionfactory就可以操作mapper里面定义的操作了
    }

    /*
    @Bean(name = "primaryManager")
    public DataSourceTransactionManager transactionManagerPrimary() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(primaryDS());
        return transactionManager;//配置事物管理器
    }
    */




    @Bean(name = "secondaryManager")
    public DataSourceTransactionManager transactionManagerSecondary() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(secondaryDS());
        return transactionManager;//配置事物管理器
    }





    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}

