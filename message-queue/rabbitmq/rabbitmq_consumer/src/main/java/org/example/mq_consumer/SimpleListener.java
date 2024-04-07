package org.example.mq_consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "simple.queue", durable = "true"),
            exchange = @Exchange(value="simple")
    ))
    public void ListenerQueue(Message message) {
        System.out.println("simple.queue:" + new String(message.getBody()));
    }

}
