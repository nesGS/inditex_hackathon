package com.hackathon.inditex.Services;

import com.hackathon.inditex.Entities.Center;

import java.util.List;
import java.util.Optional;

public interface CenterService {

    Center createCenter(Center center);

    List<Center> getAllCenters();

    Optional<Center> getCenterById(Long id);

    Center updateCenter(Long id, Center updatedCenter);

    boolean deleteCenter(Long id);

}
