package com.tianma.api.mq;

/**
 * Created by zhengpeiwei on 16/5/12.
 */
import java.util.concurrent.CountDownLatch;

public class Receiver {

        private CountDownLatch latch = new CountDownLatch(1);

        public void receiveMessage(String message) {//这里应用作api gateway的用途
            System.out.println("Received <" + message + ">");
            latch.countDown();
        }//接收消息处理消息的方法

        public CountDownLatch getLatch() {
            return latch;
        }


}
