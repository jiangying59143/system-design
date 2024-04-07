package org.example.mq_consumer.exchange;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DirectListener {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "blue.yellow.queue", durable = "true"),
            exchange = @Exchange(name = "direct_exchange", delayed = "true", type = ExchangeTypes.DIRECT),
            key = {"blue","yellow"}
    ))
    public void ListenerQueue(Message message) {
        System.out.println("blue.yellow.queue:" + new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "red.yellow.queue", durable = "true"),
            exchange = @Exchange(name = "direct_exchange", delayed = "true", type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void ListenerQueue2(Message message) {
        System.out.println("red.yellow.queue:" + new String(message.getBody()));
    }

}
