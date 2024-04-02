package org.example.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {

    public static final String QUEUE_NAME = "work_queue";

    @Bean("workQueue")
    public Queue workQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

}
