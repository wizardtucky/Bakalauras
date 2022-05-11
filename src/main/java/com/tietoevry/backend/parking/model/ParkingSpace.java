package com.tietoevry.backend.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long userWhoReserved;
    @Column
    private boolean paidSpot;
    @Column
    private boolean disabledSpot;
    @Column
    private boolean smallCarSpot;
    @Column
    private String address;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private LocalDateTime creationTime;
}
