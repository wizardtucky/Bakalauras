package com.tietoevry.backend.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ParkingSpaceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long userWhoReserved;
    private boolean paidSpot;
    private boolean disabledSpot;
    private boolean smallCarSpot;
    private String address;
    private Double latitude;
    private Double longitude;
    private LocalDateTime creationTime;
}
