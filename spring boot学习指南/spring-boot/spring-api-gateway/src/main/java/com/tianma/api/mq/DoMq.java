package com.tianma.api.mq;

/**
 * Created by zhengpeiwei on 16/5/12.
 */
import java.util.concurrent.TimeUnit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class DoMq implements CommandLineRunner {

    final static String queueName = "spring-boot";

    @Autowired
    AnnotationConfigApplicationContext context;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }//定义消息队列,非durable

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }//定义一个topic类型的exchange

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }//把消息队列绑定到exchange

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);//The connection factory drives both, allowing them to connect to the RabbitMQ server.
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);// register a Receiver(wrapped with listenerAdapter) with the message listener container to receive messages.
        return container;
    }//message listener container


    @Bean
    Receiver receiver() {
        return new Receiver();
    }//创建一个消息接收者

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }//绑一个receiver到消息监听配适器以让它可以听消息,第二个参数是接收消息的方法
    //为什么要这样做,因为receiver类知识pojo,你得包装让它具有某些特性


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(queueName, "Hello from RabbitMQ!");
        receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);//等待receiver处理消息
        context.close();
    }//使用template发送消息到queuename对应名字的队列,实际是发到exchange咯
}



/*
The message listener container and receiver beans are all you need to listen for messages.
To send a message, you also need a Rabbit template.
 */