package com.tianma.api;

/**
 * Created by Franco on 2016/3/3.
 */
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


import com.google.common.base.Predicate;
import com.tianma.api.mq.Receiver;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.TimeUnit;


@EnableSwagger2
@SpringBootApplication
@EnableZuulProxy
public class Application extends SpringBootServletInitializer implements CommandLineRunner{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }



    @Bean
    public Docket Group1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("freelink")
                .apiInfo(apiInfo())
                .select()//选择路径
                .paths(paths1())
                .build()
                ;
    }
    private Predicate<String> paths1() {
        return or(
                regex("/goodbye"),
                regex("/workorder"));

    }




    @Bean
    public Docket Group2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("freeswitch")
                .apiInfo(apiInfo2())
                .select()
                .paths(paths2())
                .build();
    }
    private Predicate<String> paths2() {
        return or(
                regex(".*"));
    }




    @Bean
    public Docket Group3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("linkcloud")
                .apiInfo(apiInfo3())
                .select()
                .paths(paths3())//把这里只选一部分就可以排除oauth/xxx那些URL
                .build();
    }
    private Predicate<String> paths3() {
        return or(
                regex("/admin.*"));
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("freelink API")
                .description("this is freelink API for develop use")
                .termsOfServiceUrl("")
                .contact("http://www.egoo.com")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }

    private ApiInfo apiInfo2() {
        return new ApiInfoBuilder()
                .title("freeswitch API")
                .description("this is freeswitch API for develop use")
                .termsOfServiceUrl("")
                .contact("http://www.egoo.com")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }

    private ApiInfo apiInfo3() {
        return new ApiInfoBuilder()
                .title("admin API")
                .description("this is admin API for develop use")
                .termsOfServiceUrl("")
                .contact("http://www.egoo.com")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }


    final static String queueName = "spring-boot";


    AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();

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
        receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);//等待receiver处理消息,receiver收消息处理是自动的 但是处理消息里面会计数latch,我们要await
        context.close();
    }//使用template发送消息到queuename对应名字的队列,实际是发到exchange咯
}



/*
The message listener container and receiver beans are all you need to listen for messages.
To send a message, you also need a Rabbit template.
 */

