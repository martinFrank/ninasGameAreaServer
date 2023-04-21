package com.github.martinfrank.games.ninasgame.areaserver.area;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AreaConfiguration {

    @Value("${com.github.martinfrank.games.ninasgame.areaserver.areanames}")
    private List<String> areasNames;

    public List<String> getAreasNames() {
        return areasNames;
    }
}
