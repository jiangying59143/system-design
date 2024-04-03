package org.example.mq_consumer.exchange;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutListener {
    @RabbitListener(queues = "fanout.queue1")
    public void listen(Message message) {
        System.out.println("fanout.queue1:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listen2(Message message) {
        System.out.println("fanout.queue2:"+new String(message.getBody()));
    }
}
