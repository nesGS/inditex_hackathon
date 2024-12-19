package com.hackathon.inditex.Services.ServiceImpl;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Repositories.CenterRepository;
import com.hackathon.inditex.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CenterServiceImpl implements CenterService {

    @Autowired
    public CenterRepository centerRepository;


    @Override
    public Center createCenter(Center center) {
        return null;
    }

    @Override
    public List<Center> getAllCenters() {
        return List.of();
    }

    @Override
    public Optional<Center> getCenterById(Long id) {
        return Optional.empty();
    }

    @Override
    public Center updateCenter(Long id, Center updatedCenter) {
        return null;
    }

    @Override
    public boolean deleteCenter(Long id) {
        return false;
    }
}
