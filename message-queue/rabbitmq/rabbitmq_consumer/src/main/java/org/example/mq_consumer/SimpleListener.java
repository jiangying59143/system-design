package org.example.mq_consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {


    @RabbitListener(queues = "simple.queue")
    public void ListenerQueue(Message message) {
        System.out.println("simple.queue:" + new String(message.getBody()));
    }

}
