package com.tianma;

import com.tianma.config.ValueConfig;
import com.tianma.event.DemoEvent;
import com.tianma.thread.CommonTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by fiboliu on 16/8/4.
 */

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String args[]) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        ValueConfig valueConfig = (ValueConfig) context.getBean("valueConfig");
        System.out.println(valueConfig.toString());

        context.publishEvent(new DemoEvent(valueConfig, "from valueConfig"));

        CommonTask commonTask = (CommonTask) context.getBean("commonTask");
        commonTask.run();
    }
}
