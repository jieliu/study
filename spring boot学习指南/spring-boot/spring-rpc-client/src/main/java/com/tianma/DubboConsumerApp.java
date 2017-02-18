package com.tianma;

import com.tianma.service.Demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fiboliu on 16/8/4.
 */

@SpringBootApplication
@ImportResource({"classpath:dubbo/consumer.xml"})
public class DubboConsumerApp {
    public static void main(String args[]) {
        ApplicationContext context = SpringApplication.run(DubboConsumerApp.class, args);
        Demo demo = (Demo) context.getBean("demo");
        System.out.println(demo.sayHello("liujie"));
    }
}