package org.example.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FanoutTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(FanoutConfig.EXCHANGE_NAME,"fanout.test","fanout mq hello~~~" + i);
        }
    }

}
