package org.example.mq_consumer.exchange;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {
    @RabbitListener(queues = "china.weather")
    public void listen(Message message) {
        System.out.println("china.weather:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "america.weather")
    public void listen2(Message message) {
        System.out.println("america.weather:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "topic.queue3")
    public void listen3(Message message) {
        System.out.println("topic.queue3:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "topic.queue4")
    public void listen4(Message message) {
        System.out.println("topic.queue4:"+new String(message.getBody()));
    }
}
