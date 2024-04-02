package org.example.mq.reliability;

import org.example.mq.config.SimpleConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final CountDownLatch listenLatch = new CountDownLatch(1);

    private String QUEUE = SimpleConfig.QUEUE_NAME;

    @Test
    public void testReturnCallback() throws ExecutionException, InterruptedException, TimeoutException {
        // send a message to the default exchange to be routed to the queue
        CorrelationData correlationData = new CorrelationData("Correlation for message 1");
        this.rabbitTemplate.convertAndSend("", QUEUE, "foo", correlationData);
        CorrelationData.Confirm confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for good delivery, ack = " + confirm.isAck());
        // send a message to the default exchange to be routed to a non-existent queue
        correlationData = new CorrelationData("Correlation for message 2");
        this.rabbitTemplate.convertAndSend("", QUEUE + QUEUE, "bar", message -> {
            System.out.println("Message after conversion: " + message);
            return message;
        }, correlationData);
        confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for send to missing queue, ack = " + confirm.isAck());
        System.out.println("Return received:"  + correlationData.getReturned());
        correlationData = new CorrelationData("Correlation for message 3");
        // send to non-existent exchange - expect nack
        this.rabbitTemplate.convertAndSend(UUID.randomUUID().toString(), QUEUE, "baz", correlationData);
        confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for send to missing exchange, ack = " + confirm.isAck());
    }

}
