package com.tianma.data.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PreDestroy;
import java.net.UnknownHostException;

/**
 * Created by fiboliu on 16-8-29.
 */
@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig {

    private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);

    @Autowired
    private MongoProperties properties;

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private MongoClientOptions options;

    private Mongo mongo;

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    @Bean
    public Mongo mongo() throws UnknownHostException {

        //联邦集群模式
//        MongoClient mongoClient = new MongoClient(Arrays.asList(
//                new ServerAddress("192.168.1.10", 27017),
//                new ServerAddress("192.168.1.11", 27017),
//                new ServerAddress("192.168.1.12", 27017)));

        MongoClient mongoClient = new MongoClient("192.168.1.3", 27017);
        if(log.isInfoEnabled()) {
            log.info("mongo client init!!");
        }
        return mongoClient;
    }
}
