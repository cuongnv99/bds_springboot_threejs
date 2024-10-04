package com.cuong.bds.service;

import com.cuong.bds.entity.HouseEntity;
import com.cuong.bds.repo.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    @Autowired
    private HouseRepo houseRepository;

    public List<HouseEntity> getAllHouses() {
        return houseRepository.findAll();
    }

    public HouseEntity saveHouse(HouseEntity house) {
        return houseRepository.save(house);
    }
}
