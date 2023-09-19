package org.example.mq_consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class RabbimtMQListener {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "boot_queue")
    public void ListenerQueue(Message message) {
        latch.countDown();
        System.out.println(new String(message.getBody()));
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
