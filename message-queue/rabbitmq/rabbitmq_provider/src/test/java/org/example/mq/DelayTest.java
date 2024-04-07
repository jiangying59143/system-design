package org.example.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DelayTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testPublisherDelayMessage() {
        // 1.创建消息
        String message = "hello, delayed message";
        // 2.发送消息，利用消息后置处理器添加消息头
        rabbitTemplate.convertAndSend("delay.direct", "delay", message, message1 -> {
            // 添加延迟消息属性
            message1.getMessageProperties().setDelay(5000);
            return message1;
        });
    }
}
