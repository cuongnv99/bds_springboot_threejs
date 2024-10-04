package com.cuong.bds.repo;

import com.cuong.bds.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<HouseEntity,Long> {
}
