package org.example.mq_consumer;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class WorkListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "work_queue", durable = "true"), exchange = @Exchange(value = "work")))
    public void listen(Message message) {
        System.out.println("listen1:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "work_queue")
    public void listen2(Message message) {
        System.out.println("listen2:"+new String(message.getBody()));
    }
}
