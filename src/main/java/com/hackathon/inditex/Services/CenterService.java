package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.CreateCenterDto;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CenterService {

    boolean existsByCoordinates(Coordinates coordinates);

    Optional<Center> createCenter(CreateCenterDto createCenterDto);

    List<Center> getAllCenters();

    Optional<Center> updateCenter(Long id, Map<String, Object> fields);

    boolean deleteCenter(Long id);

}
