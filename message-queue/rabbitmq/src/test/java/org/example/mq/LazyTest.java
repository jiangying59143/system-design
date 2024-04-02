package org.example.mq;

import org.example.mq.config.LazyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LazyTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        for (int i = 0; i < 1000000; i++) {
            rabbitTemplate.convertAndSend(LazyConfig.QUEUE_NAME,"lazy mq hello~~~" + i);
        }
    }

}
