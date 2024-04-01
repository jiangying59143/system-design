package org.example.mq_consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DirectListener {


    @RabbitListener(queues = "blue.yellow.queue")
    public void ListenerQueue(Message message) {
        System.out.println("blue.yellow.queue:" + new String(message.getBody()));
    }

    @RabbitListener(queues = "red.yellow.queue")
    public void ListenerQueue2(Message message) {
        System.out.println("red.yellow.queue:" + new String(message.getBody()));
    }

}
