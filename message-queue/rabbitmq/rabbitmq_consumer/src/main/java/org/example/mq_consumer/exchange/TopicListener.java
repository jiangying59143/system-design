package org.example.mq_consumer.exchange;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "china.weather", durable = "true"),
            exchange = @Exchange(name = "topic_exchange", delayed = "true", type = ExchangeTypes.TOPIC),
            key = {"china.weather"}
    ))
    public void listen(Message message) {
        System.out.println("china.weather:"+new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "america.weather", durable = "true"),
            exchange = @Exchange(name = "topic_exchange", delayed = "true", type = ExchangeTypes.TOPIC),
            key = {"america.weather"}
    ))
    public void listen2(Message message) {
        System.out.println("america.weather:"+new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue3", durable = "true"),
            exchange = @Exchange(name = "topic_exchange", delayed = "true", type = ExchangeTypes.TOPIC),
            key = {"topic.*"}
    ))
    public void listen3(Message message) {
        System.out.println("topic.queue3:"+new String(message.getBody()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue4", durable = "true"),
            exchange = @Exchange(name = "topic_exchange", delayed = "true", type = ExchangeTypes.TOPIC),
            key = {"topic.#"}
    ))
    public void listen4(Message message) {
        System.out.println("topic.queue4:"+new String(message.getBody()));
    }
}
