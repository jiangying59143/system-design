package org.example.mq.config.reliability;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者 return callback
 * 一般不用开启
 */
@Slf4j
@Configuration
public class RabbitMqReturnConfirmConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error(
                            "\n收到的消息return callback: " +
                            "\n  exchange={}" +
                            "\n  message={}" +
                            "\n  replyCode={}" +
                            "\n  replyText={}" +
                            "\n  routingKey={}\n", returned.getExchange(), returned.getMessage(),
                returned.getReplyCode(), returned.getReplyText(), returned.getRoutingKey());
        });

        rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
            if(ack){
                System.out.println("Received " + (ack ? " ack " : " nack ") + "for correlation: " + correlation);
            }else if(correlation != null && correlation.getReturned() != null){
                //重发
                rabbitTemplate.convertAndSend(correlation.getReturned().getMessage());
            }
        });
    }
}
