package org.example.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LazyConfig {
    public static final String QUEUE_NAME = "lazy.queue";

    @Bean("lazyQueue")
    public Queue lazyQueue(){
        return QueueBuilder.durable(QUEUE_NAME).lazy().build();
    }


}
