package com.github.martinfrank.games.ninasgame.areaserver.map.controller;

import com.github.martinfrank.games.ninasgame.areaserver.map.model.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps") //https://restfulapi.net/resource-naming/ should be PLURAL!
public class MapController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

//    @Autowired
//    RabbitMQSender rabbitMQSender;


    @GetMapping("/generate")
    public ResponseEntity<Map> getMap() {
        System.out.println("get map...");
//        rabbitMQSender.send(new com.github.martinfrank.games.rpgmodel.map.Map(1L, 32, 32));
        return ResponseEntity.ok().body(new Map(64, 64));
    }

}
