package com.github.martinfrank.games.rpgservice.queue;

import com.github.martinfrank.games.rpgservice.map.model.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
//@Component
//@RabbitListener(queues = "rabbitmq.queue", id = "listener")
//public class RabbitMQReceiver {
//    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
//    @RabbitHandler
//    public void receiver(Map map) {
//        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + map.getWidth()+"/"+map.getHeight());
//    }
//}

public class RabbitMQReceiver {

}