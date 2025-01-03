package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Services.OrderAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderAssignmentController {

    @Autowired
    private OrderAssignmentService orderAssignmentService;

    @PostMapping("/order-assignations")
    public ResponseEntity<Map<String, Object>> assignOrders() {
        Map<String, Object> result = orderAssignmentService.assignPendingOrders();
        return ResponseEntity.ok(result);
    }

}





