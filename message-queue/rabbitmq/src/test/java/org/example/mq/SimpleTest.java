package org.example.mq;

import org.example.mq.config.SimpleConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(SimpleConfig.QUEUE_NAME,"simple mq hello~~~" + i);
        }
    }

    @Test
    public void testTransient(){
        Message message = MessageBuilder.withBody("I'm transient".getBytes(StandardCharsets.UTF_8)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
        rabbitTemplate.convertAndSend(SimpleConfig.QUEUE_NAME,message);
    }

    @Test
    public void testMessageConverter(){
        Map<String, String> map = new HashMap<>();
        map.put("name","jiangYing");
        map.put("age", "0");
        rabbitTemplate.convertAndSend(SimpleConfig.QUEUE_NAME,map);
    }

    @Test
    public void testReturnCallback(){
        rabbitTemplate.convertAndSend(SimpleConfig.QUEUE_NAME + "no","return call hello~~~");
    }
}
