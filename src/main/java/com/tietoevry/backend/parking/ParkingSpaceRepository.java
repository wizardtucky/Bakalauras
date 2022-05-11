package com.tietoevry.backend.parking;

import com.tietoevry.backend.parking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
