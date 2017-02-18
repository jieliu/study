package com.tianma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * Created by fiboliu on 16/8/4.
 */

@SpringBootApplication
@ImportResource("classpath:dubbo/provider.xml")
public class DubboProviderApp {

    private static final Logger logger = LoggerFactory.getLogger(DubboProviderApp.class);

    public static void main(String args[]) {

        SpringApplication.run(DubboProviderApp.class, args);

        try {
            if(logger.isInfoEnabled()) {
                logger.info("启动成功,按任意键关闭");
            }
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
