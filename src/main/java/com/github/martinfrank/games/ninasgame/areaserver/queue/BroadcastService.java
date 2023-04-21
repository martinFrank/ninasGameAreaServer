package com.github.martinfrank.games.ninasgame.areaserver.queue;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Configuration
public class BroadcastService {

    public static final String FANOUT_EXCHANGE = "ninasgame.areaserver.fanout.exchange";
    private static final boolean NON_DURABLE = false;
    private static final boolean AUTODELETE = true;

//    @Bean
    public Declarables createQueue(String queueName) {

        Queue fanoutQueue = new Queue(queueName, NON_DURABLE);
        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE, NON_DURABLE, AUTODELETE);
        return new Declarables(fanoutQueue, fanoutExchange, BindingBuilder
                .bind(fanoutQueue)
                .to(fanoutExchange)
        );
    }
}
