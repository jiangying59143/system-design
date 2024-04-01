package org.example.mq.exchange;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String QUEUE_NAME1 = "blue.yellow.queue";

    public static final String QUEUE_NAME2 = "red.yellow.queue";

    //1.交换机
    @Bean("directExchange")
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("directQueue1")
    public Queue directQueue1(){
        return QueueBuilder.durable(QUEUE_NAME1).build();
    }

    @Bean("directQueue2")
    public Queue directQueue2(){
        return QueueBuilder.durable(QUEUE_NAME2).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key 相当于 exchange 和 queue之间的 filter
     */
    @Bean
    public Binding bindDirectQueueExchange1(@Qualifier("directQueue1") Queue queue, @Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("blue").noargs();
    }

    @Bean
    public Binding bindDirectQueueExchange2(@Qualifier("directQueue1") Queue queue, @Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yellow").noargs();
    }

    @Bean
    public Binding bindDirectQueueExchange3(@Qualifier("directQueue2") Queue queue, @Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("red").noargs();
    }

    @Bean
    public Binding bindDirectQueueExchange4(@Qualifier("directQueue2") Queue queue, @Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yellow").noargs();
    }


}
