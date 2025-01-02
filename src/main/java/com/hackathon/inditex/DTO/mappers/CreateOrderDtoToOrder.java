package com.hackathon.inditex.DTO.mappers;

import com.hackathon.inditex.DTO.CreateOrderDto;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.Entities.Order;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderDtoToOrder implements IMapper<CreateOrderDto, Order> {


    @Override
    public Order map(CreateOrderDto in) {
        Order order = new Order();
        order.setCustomerId(in.getCustomerId());
        order.setSize(in.getSize());
        order.setCoordinates(new Coordinates(
                in.getCoordinates().getLatitude(),
                in.getCoordinates().getLongitude()
        ));
        return order;
    }
}
