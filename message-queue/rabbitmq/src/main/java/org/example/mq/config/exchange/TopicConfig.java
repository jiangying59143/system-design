package org.example.mq.config.exchange;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String QUEUE_NAME1 = "china.weather";
    public static final String QUEUE_NAME2 = "america.weather";
    public static final String QUEUE_NAME3 = "topic.queue3";
    public static final String QUEUE_NAME4 = "topic.queue4";

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

    @Bean("topicQueue2")
    public Queue topicQueue2(){
        return QueueBuilder.durable(QUEUE_NAME2).build();
    }

    @Bean("topicQueue3")
    public Queue topicQueue3(){
        return QueueBuilder.durable(QUEUE_NAME3).build();
    }

    @Bean("topicQueue4")
    public Queue topicQueue4(){
        return QueueBuilder.durable(QUEUE_NAME4).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key 相当于 exchange 和 queue之间的 filter
     */
    @Bean
    public Binding bindtopicQueueExchange1(@Qualifier("topicQueue1") Queue queue, @Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("china.weather").noargs();
    }

    @Bean
    public Binding bindtopicQueueExchange2(@Qualifier("topicQueue2") Queue queue, @Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("america.weather").noargs();
    }

    @Bean
    public Binding bindtopicQueueExchange3(@Qualifier("topicQueue3") Queue queue, @Qualifier("topicExchange") Exchange exchange){
        //* 代表一个单词
        return BindingBuilder.bind(queue).to(exchange).with("topic.*").noargs();
    }

    @Bean
    public Binding bindtopicQueueExchange4(@Qualifier("topicQueue4") Queue queue, @Qualifier("topicExchange") Exchange exchange){
        // * 代表多个单词
        return BindingBuilder.bind(queue).to(exchange).with("topic.#").noargs();
    }


}
