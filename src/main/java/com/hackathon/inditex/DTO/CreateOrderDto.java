package com.hackathon.inditex.DTO;

import com.hackathon.inditex.Entities.Coordinates;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CreateOrderDto {

    private Long customerId;
    private String size;
    private Coordinates coordinates;
}
