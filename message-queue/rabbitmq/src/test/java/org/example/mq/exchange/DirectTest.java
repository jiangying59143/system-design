package org.example.mq.exchange;

import org.example.mq.exchange.DirectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DirectTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_NAME,"blue","blue mq hello~~~" + i);
        }

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_NAME,"yellow","yellow mq hello~~~" + i);
        }

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_NAME,"red","red mq hello~~~" + i);
        }
    }
}
