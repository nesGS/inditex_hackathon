package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.CreateOrderDto;
import com.hackathon.inditex.Entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> createOrder(CreateOrderDto createOrderDto);

    List<Order> getAllOrders();


}
