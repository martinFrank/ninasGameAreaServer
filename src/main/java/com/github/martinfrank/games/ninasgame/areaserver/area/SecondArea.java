package com.github.martinfrank.games.ninasgame.areaserver.area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.martinfrank.games.ninasgame.areaserver.queue.BroadcastConfig.FANOUT_EXCHANGE_NAME_2;
import static com.github.martinfrank.games.ninasgame.areaserver.queue.BroadcastConfig.FANOUT_QUEUE_2_NAME;

@Component
@EnableScheduling
public class SecondArea {

    private static final Logger log = LoggerFactory.getLogger(SecondArea.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private RabbitAdmin admin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 3100)
    public void reportCurrentTime() {

        admin.purgeQueue(FANOUT_QUEUE_2_NAME);

        String now = dateFormat.format(new Date());
        log.info("The time is now {}", now);

        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME_2, "", "seconarea: "+now);
    }

}
