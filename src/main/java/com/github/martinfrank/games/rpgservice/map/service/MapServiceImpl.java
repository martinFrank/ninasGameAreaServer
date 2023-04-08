package com.github.martinfrank.games.rpgservice.map.service;

import com.github.martinfrank.games.rpgservice.map.model.Map;
import com.github.martinfrank.games.rpgservice.map.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MapServiceImpl implements MapService {


    @Autowired
    private MapRepository mapRepository;


    public MapServiceImpl(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    @Override
    public Map create(Map map) {
        return mapRepository.save(map);
    }

    @Override
    public Map update(Map map) {
        Optional<Map> mapCandidate = mapRepository.findById(map.getId());

        if (mapCandidate.isPresent()) {
            Map mapToUpdate = mapCandidate.get();
            mapToUpdate.setId(map.getId());
            mapToUpdate.setWidth(map.getWidth());
            mapToUpdate.setHeight(map.getHeight());
            return mapToUpdate;
        } else {
            throw new ResourceNotFoundException("Person Record not found with id : " + map.getId());
        }
    }

    @Override
    public List<Map> getAll() {
        return mapRepository.findAll();
    }

    @Override
    public Map getById(long id) {
        Optional<Map> candidate = this.mapRepository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new ResourceNotFoundException("Person Record not found with id : " + id);
        }
    }

    @Override
    public void delete(long id) {
        Optional<Map> candidate = this.mapRepository.findById(id);
        if (candidate.isPresent()) {
            this.mapRepository.delete(candidate.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
