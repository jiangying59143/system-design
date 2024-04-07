package org.example.mq_consumer.exchange;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1", durable = "true"),
            exchange = @Exchange(name = "fanout.exchange", delayed = "true", type = ExchangeTypes.FANOUT),
            key = {"fanout.#"}
    ))
    public void listen(Message message) {
        System.out.println("fanout.queue1:"+new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue2", durable = "true"),
            exchange = @Exchange(name = "fanout.exchange", delayed = "true", type = ExchangeTypes.FANOUT),
            key = {"fanout.#"}
    ))
    public void listen2(Message message) {
        System.out.println("fanout.queue2:"+new String(message.getBody()));
    }
}
