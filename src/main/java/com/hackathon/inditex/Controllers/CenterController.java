package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.DTO.CreateCenterDto;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<?> createCenter(@RequestBody CreateCenterDto createCenterDto) {
        // Validación: currentLoad no puede exceder maxCapacity
        if (createCenterDto.getCurrentLoad() > createCenterDto.getMaxCapacity()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("Current load cannot exceed max capacity."));
        }

        // Validación: no puede haber más de un centro en la misma posición
        boolean centerExists = centerService.existsByCoordinates(
                createCenterDto.getCoordinates());
        if (centerExists) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("There is already a logistics center in that position."));
        }

        // Creación de Center
        Center createdCenter = centerService.createCenter(createCenterDto)
                .orElseThrow(() -> new RuntimeException("Unexpected error occurred while creating the center"));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Logistics center created successfully."));
    }

    @GetMapping
    public ResponseEntity<List<Center>> getAllCenters() {
        List<Center> centers = centerService.getAllCenters();
        if (centers.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(centers); // 200 OK con la lista de centros
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCenter(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Optional<Center> updatedCenterOptional = centerService.updateCenter(id, fields);
    
        if (updatedCenterOptional.isPresent()) {
            Center updatedCenter = updatedCenterOptional.get();
            
            // Validación: currentLoad no puede exceder maxCapacity
            if (updatedCenter.getCurrentLoad() > updatedCenter.getMaxCapacity()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse("Current load cannot exceed max capacity."));
            }
            
            ApiResponse response = new ApiResponse("Center updated successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("Center not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCenter(@PathVariable Long id) {
        ApiResponse response = new ApiResponse("Logistics center deleted successfully.");

        return centerService.deleteCenter(id) ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}






















