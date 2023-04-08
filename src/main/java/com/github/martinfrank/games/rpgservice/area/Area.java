package com.github.martinfrank.games.rpgservice.area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.martinfrank.games.rpgservice.queue.BroadcastConfig.FANOUT_EXCHANGE_NAME;
import static com.github.martinfrank.games.rpgservice.queue.BroadcastConfig.TOPIC_EXCHANGE_NAME;

@Component
@EnableScheduling
public class Area {

    private static final Logger log = LoggerFactory.getLogger(Area.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    private static String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "fanout" + "message");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_WARN,
                "topic important warn" + "message");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_ERROR,
                "topic important error" + "message");
    }

}
