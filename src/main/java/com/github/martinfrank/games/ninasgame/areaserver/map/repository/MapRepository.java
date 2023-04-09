package com.github.martinfrank.games.ninasgame.areaserver.map.repository;

import com.github.martinfrank.games.ninasgame.areaserver.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
