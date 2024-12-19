package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<Center> createCenter(@RequestBody Center center) {
        Center createdCenter = centerService.createCenter(center);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCenter);
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
    public ResponseEntity<Center> updateCenter(@PathVariable Long id, @RequestBody Center center) {
        return ResponseEntity.ok(centerService.updateCenter(id, center));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        return centerService.deleteCenter(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


