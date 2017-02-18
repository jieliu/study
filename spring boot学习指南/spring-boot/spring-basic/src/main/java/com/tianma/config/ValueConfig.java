package com.tianma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;


/**
 * Created by fiboliu on 16/8/4.
 */
@Configuration
@PropertySource("classpath:com/tianma/config/config.properties")
public class ValueConfig {

    @Value("liujie")
    private String name;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private Double randomNumber;

//    @Value("#{beanConfig.name}")
//    private String fromAnother;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Value("classpath:com/tianma/config/config.properties")
    private Resource configProperties;

    @Value("${config.user}")
    private String user;

    @Autowired
    private Environment environment;

    @Override
    public String toString() {
        return "ValueConfig{" +
                "name='" + name + '\'' +
                ", osName='" + osName + '\'' +
                ", randomNumber=" + randomNumber +
                ", configProperties='" + configProperties + '\'' +
                ", testUrl=" + testUrl +
                ", user='" + user + '\'' +
                ", environment=" + environment +
                '}';
    }
}
