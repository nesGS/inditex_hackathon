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
import org.springframework.util.ReflectionUtils;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Center> updateCenter(Long id, Map<String, Object> fields) {
        Optional<Center> existingCenter = centerRepository.findById(id);

        if (existingCenter.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Center.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingCenter.get(), value);
            });
            return Optional.of(centerRepository.save(existingCenter.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCenter(Long id) {
        Optional<Center> centerOptional = centerRepository.findById(id);
        if (centerOptional.isPresent()) {
            centerRepository.delete(centerOptional.get());
            return true;
        }
        return false;
    }






}