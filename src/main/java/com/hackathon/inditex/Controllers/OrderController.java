package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.DTO.CreateOrderDto;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        Optional<Order> createdOrder = orderService.createOrder(createOrderDto);
        if (createdOrder.isPresent()) {
            Order order = createdOrder.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

/////////////////////////////////////////////////////////////////////////////
///// BUSCAR ALTERNATIVA A LA CLASE ESTÁTICA PARA GESTIONAR LA RESPUESTA ////
////////////////////////////////////////////////////////////////////////////


    // Clase que gestiona el formato solicitado para la respuesta en "createOrder"
    private static class OrderResponse {
        public Long orderId;
        public Long customerId;
        public String size;
        public String assignedLogisticsCenter;
        public Coordinates coordinates;
        public String status;
        public String message;

        public OrderResponse(Order order) {
            this.orderId = order.getId();
            this.customerId = order.getCustomerId();
            this.size = order.getSize();
            this.assignedLogisticsCenter = order.getAssignedCenter();
            this.coordinates = order.getCoordinates();
            this.status = order.getStatus();
            this.message = "Order created successfully in PENDING status.";
        }
    }

    @PostMapping("/order-assignations")
    public ResponseEntity<Order> orderAssignations() {

        // Asignar centros a los pedidos en función del tamaño


        return null;
    }





}
