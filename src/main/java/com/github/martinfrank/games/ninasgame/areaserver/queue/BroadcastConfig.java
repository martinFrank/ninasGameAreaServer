package com.github.martinfrank.games.ninasgame.areaserver.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BroadcastConfig {

    private static final boolean NON_DURABLE = false;

    public final static String FANOUT_QUEUE_1_NAME = "com.baeldung.spring-amqp-simple.fanout.queue1";
    public final static String FANOUT_QUEUE_2_NAME = "com.baeldung.spring-amqp-simple.fanout.queue2";
    public final static String FANOUT_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.fanout.exchange";
    public final static String FANOUT_EXCHANGE_NAME_2 = "com.baeldung.spring-amqp-simple.fanout.exchange2";
    public final static String TOPIC_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.topic.exchange";

    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_1_NAME, NON_DURABLE);

        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME, NON_DURABLE, true);

        return new Declarables(fanoutQueue1, fanoutExchange, BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange)
                );
    }

    @Bean
    public Declarables fanoutBindings2() {
        Queue fanoutQueue2 = new Queue(FANOUT_QUEUE_2_NAME, NON_DURABLE);

        FanoutExchange fanoutExchange2 = new FanoutExchange(FANOUT_EXCHANGE_NAME_2, NON_DURABLE, true);

        return new Declarables(fanoutQueue2, fanoutExchange2, BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange2)
        );
    }

}
