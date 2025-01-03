package com.hackathon.inditex.Services.ServiceImpl;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Repositories.CenterRepository;
import com.hackathon.inditex.Repositories.OrderRepository;
import com.hackathon.inditex.Services.OrderAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderAssignmentServiceImpl implements OrderAssignmentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CenterRepository centerRepository;

    @Transactional
    @Override
    public Map<String, Object> assignPendingOrders() {
        List<Order> pendingOrders = orderRepository.findByStatus("PENDING");
        List<Center> availableCenters = centerRepository.findByStatus("AVAILABLE");
        List<Map<String, Object>> processedOrders = new ArrayList<>();

        for (Order order : pendingOrders) {
            Center assignedCenter = findSuitableCenter(order, availableCenters);
            Map<String, Object> processedOrder = new HashMap<>();
            processedOrder.put("orderId", order.getId());

            if (assignedCenter != null) {
                double distance = calculateDistance(order.getCoordinates(), assignedCenter.getCoordinates());
                order.setAssignedCenter(assignedCenter);
                order.setStatus("ASSIGNED");
                assignedCenter.setCurrentLoad(assignedCenter.getCurrentLoad() + 1);

                processedOrder.put("distance", distance);
                processedOrder.put("assignedLogisticsCenter", assignedCenter.getName());
                processedOrder.put("status", "ASSIGNED");
            } else {
                processedOrder.put("distance", null);
                processedOrder.put("assignedLogisticsCenter", null);
                processedOrder.put("status", "PENDING");
                
                if (!isSuitableCenterAvailable(order, availableCenters)) {
                    processedOrder.put("message", "No available centers support the order type.");
                } else {
                    processedOrder.put("message", "All centers are at maximum capacity.");
                }
            }

            processedOrders.add(processedOrder);
            orderRepository.save(order);
        }

        centerRepository.saveAll(availableCenters);

        Map<String, Object> result = new HashMap<>();
        result.put("processed-orders", processedOrders);
        return result;
    }

    private Center findSuitableCenter(Order order, List<Center> centers) {
        Center suitableCenter = null;
        double minDistance = Double.MAX_VALUE;

        for (Center center : centers) {
            if (center.getCapacity().contains(order.getSize()) && center.getCurrentLoad() < center.getMaxCapacity()) {
                double distance = calculateDistance(order.getCoordinates(), center.getCoordinates());
                if (distance < minDistance) {
                    minDistance = distance;
                    suitableCenter = center;
                }
            }
        }

        return suitableCenter;
    }

    private boolean isSuitableCenterAvailable(Order order, List<Center> centers) {
        return centers.stream().anyMatch(center -> center.getCapacity().contains(order.getSize()));
    }

    private double calculateDistance(Map<String, Double> coord1, Map<String, Double> coord2) {
        double lat1 = Math.toRadians(coord1.get("latitude"));
        double lon1 = Math.toRadians(coord1.get("longitude"));
        double lat2 = Math.toRadians(coord2.get("latitude"));
        double lon2 = Math.toRadians(coord2.get("longitude"));

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Radius of earth in kilometers
        double radius = 6371;

        return radius * c;
    }
}