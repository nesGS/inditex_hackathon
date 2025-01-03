package com.hackathon.inditex.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "centers", uniqueConstraints = @UniqueConstraint(columnNames = {"latitude", "longitude"}))
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String capacity;

    private String status;

    private Integer currentLoad;

    private Integer maxCapacity;

    @Embedded
    private Coordinates coordinates;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedCenter")
    private List<Order> assignedOrders;
}
