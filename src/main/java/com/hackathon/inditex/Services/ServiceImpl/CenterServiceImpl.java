package com.hackathon.inditex.Services.ServiceImpl;

import com.hackathon.inditex.DTO.CreateCenterDto;
import com.hackathon.inditex.DTO.mappers.CreateCenterDtoToCenter;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.Repositories.CenterRepository;
import com.hackathon.inditex.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private CreateCenterDtoToCenter mapToCenter;

    public boolean existsByCoordinates(Coordinates coordinates) {
        return centerRepository.existsByCoordinates(coordinates);
    }


    @Transactional
    @Override
    public Optional<Center> createCenter(CreateCenterDto createCenterDto) {
        Center center = mapToCenter.map(createCenterDto);
        Center savedCenter = centerRepository.save(center);
        return Optional.of(savedCenter);
    }

//    @Override
//    public List<Center> getAllCenters() {
//        return null;
//    }
//
//    @Override
//    public Optional<Center> updateCenter(Long id, Center updatedCenter) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean deleteCenter(Long id) {
//        return false;
//    }

}