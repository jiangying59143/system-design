package org.example.mq_consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.rabbitmq.listener.simple.retry.enabled", havingValue = "true")
public class ErrorDirectTopicConfig {

    public static final String EXCHANGE_NAME = "error.direct";
    public static final String QUEUE_NAME1 = "error.queue";
    public static final String ROUTING_KEY = "error";

    // 设置重新投递的交换机
    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate, EXCHANGE_NAME, ROUTING_KEY);
    }

    //1.交换机
    @Bean("topicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("topicQueue1")
    public Queue topicQueue1(){
        return QueueBuilder.durable(QUEUE_NAME1).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key 相当于 exchange 和 queue之间的 filter
     */
    @Bean
    public Binding bindTopicQueueExchange1(@Qualifier("topicQueue1") Queue queue, @Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
