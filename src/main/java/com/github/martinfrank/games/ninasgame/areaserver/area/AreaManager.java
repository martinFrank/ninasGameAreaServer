package com.github.martinfrank.games.ninasgame.areaserver.area;

import com.github.martinfrank.games.ninasgame.areaserver.queue.BroadcastService;
import com.github.martinfrank.games.ninasgame.areaserver.restclient.NinasGameServerRestService;
import com.github.martinfrank.games.ninasgame.areaserver.restclient.NinasGameServerRestServiceImpl;
import com.github.martinfrank.ninasgame.model.map.Map;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AreaManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaManager.class);

    @Autowired
    private
    AreaConfiguration areaConfiguration;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    private final NinasGameServerRestService restClient = new NinasGameServerRestServiceImpl();

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    private final BroadcastService broadcastService = new BroadcastService();


    @PostConstruct
    public void createAreaWorker(){
        for(String areaName: areaConfiguration.getAreasNames()){
            LOGGER.debug("start Area Worker for area (name): {}", areaName);
            startNewAreaWorker(areaName);

        }
    }

    private void startNewAreaWorker(String areaName) {
        try {
            Map map = restClient.loadMap(areaName);
            LOGGER.debug("map loaded successfully...");

            String queueName = map.getQueueName();
            String exchangeName = BroadcastService.FANOUT_EXCHANGE+"."+map.getName();

            Queue fanoutQueue = new Queue(queueName, true, false, false );
            rabbitAdmin.declareQueue(fanoutQueue);
            FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);
            rabbitAdmin.declareExchange(fanoutExchange);
            rabbitAdmin.declareBinding(BindingBuilder.bind(fanoutQueue).to(fanoutExchange));


            AreaWorker areaWorker = new AreaWorker(rabbitTemplate, rabbitAdmin, map, exchangeName);
            scheduledExecutorService.scheduleAtFixedRate(areaWorker, 1000, 3000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            LOGGER.error("exception during create area worker",e);
        }

    }


}
