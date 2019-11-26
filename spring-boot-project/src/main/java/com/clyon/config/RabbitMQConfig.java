package com.clyon.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-17 20:45
 **/
@Configuration
public class RabbitMQConfig {
		 
    @Value("${spring.rabbitmq.host}")
    private String host;
 
    @Value("${spring.rabbitmq.port}")
    private int port;
 
    @Value("${spring.rabbitmq.username}")
    private String username;
 
    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String QUEUE_NAME1 = "queueTopicsSPD1";
    public static final String QUEUE_NAME2 = "queueTopicsSPD2";
    public static final String EXCHANGE_TOPICS="exchangeTopicsSPD";
    public static final String ROUTINGKEY_EMAIL="inform.#.email.#";
    public static final String ROUTINGKEY_SMS="inform.#.sms.#";
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
    
    @Bean
   // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope("prototype")
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    //声明交换机
    @Bean(EXCHANGE_TOPICS)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS).durable(true).build();
    }

    //声明QUEUE_INFORM_EMAIL队列
    @Bean(QUEUE_NAME1)
    public Queue QUEUE_NAME1(){
        return new Queue(QUEUE_NAME1);
    }
    //声明QUEUE_INFORM_SMS队列
    @Bean(QUEUE_NAME2)
    public Queue QUEUE_NAME2(){
        return new Queue(QUEUE_NAME2);
    }

    //ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_NAME1) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }
    //ROUTINGKEY_SMS队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_ROUTINGKEY_SMS(@Qualifier(QUEUE_NAME2) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
    }


}
