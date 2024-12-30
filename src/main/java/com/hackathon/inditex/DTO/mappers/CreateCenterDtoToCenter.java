package com.hackathon.inditex.DTO.mappers;

import com.hackathon.inditex.DTO.CreateCenterDto;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class CreateCenterDtoToCenter implements IMapper<CreateCenterDto, Center> {

    @Override
    public Center map(CreateCenterDto in) {
        Center center = new Center();
        center.setName(in.getName());
        center.setCapacity(in.getCapacity());
        center.setStatus(in.getStatus());
        center.setMaxCapacity(in.getMaxCapacity());
        center.setCurrentLoad(in.getCurrentLoad());
        center.setCoordinates(new Coordinates(
                in.getCoordinates().getLatitude(),
                in.getCoordinates().getLongitude()
        ));
        return center;
    }
}
