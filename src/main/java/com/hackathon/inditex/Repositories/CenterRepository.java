package com.hackathon.inditex.Repositories;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinates(Coordinates coordinates);

    List<Center> findByStatus(String status);


}
