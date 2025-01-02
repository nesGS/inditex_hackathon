package com.hackathon.inditex.Services.ServiceImpl;

import com.hackathon.inditex.DTO.CreateOrderDto;
import com.hackathon.inditex.DTO.mappers.CreateOrderDtoToOrder;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Repositories.OrderRepository;
import com.hackathon.inditex.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CreateOrderDtoToOrder mapToOrder;


    @Transactional
    @Override
    public Optional<Order> createOrder(CreateOrderDto createOrderDto) {
        Order order = mapToOrder.map(createOrderDto);
        order.setStatus("PENDING");  // Se crea siempre los pedidos con estado PENDING
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
