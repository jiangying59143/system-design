package org.example.mq_consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {


    @RabbitListener(queuesToDeclare = @Queue(
            name = "simple.queue",
            durable = "true"
    ))
    public void ListenerQueue(Message message) {
        System.out.println("simple.queue:" + new String(message.getBody()));
    }

}
