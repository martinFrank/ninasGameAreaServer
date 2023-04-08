package com.github.martinfrank.games.rpgservice.map.repository;

import com.github.martinfrank.games.rpgservice.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
