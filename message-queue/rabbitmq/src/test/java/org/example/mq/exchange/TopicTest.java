package org.example.mq.exchange;

import org.example.mq.exchange.TopicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TopicTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"china.weather","china.weather mq hello~~~" + i);
        }

        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"america.weather","america.weather mq hello~~~" + i);
        }

        // 下面的无法发送到 china.weather 和 america.weather， * 和 # 只有在绑定queue的时候起作用
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"*.weather","weather mq hello~~~" + i);
        }

        //
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"topic.x.y","topic.x.y mq hello~~~" + i);
        }

        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"topic.x","topic.x mq hello~~~" + i);
        }
    }
}
