package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.CreateCenterDto;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;

import java.util.List;
import java.util.Optional;

public interface CenterService {

    public boolean existsByCoordinates(Coordinates coordinates);

    Optional<Center> createCenter(CreateCenterDto createCenterDto);

//    List<Center> getAllCenters();
//
//    Optional<Center> updateCenter(Long id, Center updatedCenter);
//
//    boolean deleteCenter(Long id);

}
