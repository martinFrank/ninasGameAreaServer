package com.github.martinfrank.games.ninasgame.areaserver.area;

import com.github.martinfrank.games.ninasgame.areaserver.util.FileUtil;
import com.github.martinfrank.ninasgame.model.map.Map;
import org.mapeditor.io.TMXMapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AreaWorker implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(AreaWorker.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final RabbitTemplate rabbitTemplate;
    private final RabbitAdmin rabbitAdmin;

    private final Map map;
    private final org.mapeditor.core.Map tiledmap;

//    private

    public AreaWorker(RabbitTemplate rabbitTemplate, RabbitAdmin rabbitAdmin, Map map) throws Exception {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitAdmin = rabbitAdmin;
        this.map = map;
        TMXMapReader mapReader = new TMXMapReader();
        this.tiledmap = mapReader.readMap(FileUtil.getFile(map.getMapFilename()).getAbsolutePath());
    }


    public void run() {

//        rabbitAdmin.purgeQueue(FANOUT_QUEUE_1_NAME);

        String now = dateFormat.format(new Date());
        log.info("The time is now {} (from {})", now, map.getQueueName());
//        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "firstArea: "+now);
    }

}
