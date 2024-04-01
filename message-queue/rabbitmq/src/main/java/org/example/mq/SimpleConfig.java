package org.example.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {
    public static final String QUEUE_NAME = "simple.queue";

    @Bean("simpleQueue")
    public Queue simpleQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
}
