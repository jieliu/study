package com.tianma.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.UnknownHostException;

/**
 * Created by fiboliu on 16-8-29.
 */

@SpringBootApplication
public class Application {

    public static void main(String args[]) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
    }
}
