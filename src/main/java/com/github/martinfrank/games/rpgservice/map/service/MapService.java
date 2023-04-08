package com.github.martinfrank.games.rpgservice.map.service;

import com.github.martinfrank.games.rpgservice.map.model.Map;

import java.util.List;

public interface MapService {

    Map create(Map person);

    Map update(Map person);

    List<Map> getAll();

    Map getById(long id);

    void delete(long id);

}
