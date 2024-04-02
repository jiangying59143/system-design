package org.example.mq.config.exchange;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    public static final String EXCHANGE_NAME = "fanout.exchange";
    public static final String QUEUE_NAME1 = "fanout.queue1";
    public static final String QUEUE_NAME2 = "fanout.queue2";

    //1.交换机
    @Bean("fanoutExchange")
    public Exchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("fanoutQueue1")
    public Queue fanoutQueue1(){
        return QueueBuilder.durable(QUEUE_NAME1).build();
    }

    @Bean("fanoutQueue2")
    public Queue fanoutQueue2(){
        return QueueBuilder.durable(QUEUE_NAME2).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key 相当于 exchange 和 queue之间的 filter
     */
    @Bean
    public Binding bindfanoutQueueExchange1(@Qualifier("fanoutQueue1") Queue queue, @Qualifier("fanoutExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("fanout.#").noargs();
    }

    @Bean
    public Binding bindfanoutQueueExchange2(@Qualifier("fanoutQueue2") Queue queue, @Qualifier("fanoutExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("fanout.#").noargs();
    }
}
