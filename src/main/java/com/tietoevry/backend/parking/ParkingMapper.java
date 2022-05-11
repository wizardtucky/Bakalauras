package com.tietoevry.backend.parking;

import com.tietoevry.backend.parking.model.ParkingSpace;
import com.tietoevry.backend.parking.model.ParkingSpaceDto;
import com.tietoevry.backend.user.UserMapper;

public class ParkingMapper {

    public static ParkingSpace toParkingSpace(ParkingSpaceDto parkingSpaceDto){
        return ParkingSpace.builder()
            .paidSpot(parkingSpaceDto.isPaidSpot())
            .disabledSpot(parkingSpaceDto.isDisabledSpot())
            .smallCarSpot(parkingSpaceDto.isSmallCarSpot())
            .userWhoReserved(parkingSpaceDto.getUserWhoReserved())
            .address(parkingSpaceDto.getAddress())
            .latitude(parkingSpaceDto.getLatitude())
            .longitude(parkingSpaceDto.getLongitude())
            .creationTime(parkingSpaceDto.getCreationTime())
            .build();
    }

    public static ParkingSpaceDto toParkingSpaceDto(ParkingSpace parkingSpace){
        return ParkingSpaceDto.builder()
            .id(parkingSpace.getId())
            .userId(parkingSpace.getUserId())
            .userWhoReserved(parkingSpace.getUserWhoReserved())
            .paidSpot(parkingSpace.isPaidSpot())
            .disabledSpot(parkingSpace.isDisabledSpot())
            .smallCarSpot(parkingSpace.isSmallCarSpot())
            .userWhoReserved(parkingSpace.getUserWhoReserved())
            .address(parkingSpace.getAddress())
            .latitude(parkingSpace.getLatitude())
            .longitude(parkingSpace.getLongitude())
            .creationTime(parkingSpace.getCreationTime())
//            .user(UserMapper.toUserDto(parkingSpace.getUser()))
            .build();
    }

    public static ParkingSpace toUpdateParkingSpace(ParkingSpaceDto updatedParkingSpace, ParkingSpace parkingSpace){
        parkingSpace.setDisabledSpot(updatedParkingSpace.isDisabledSpot());
        parkingSpace.setPaidSpot(updatedParkingSpace.isPaidSpot());
        parkingSpace.setSmallCarSpot(updatedParkingSpace.isSmallCarSpot());
        parkingSpace.setAddress(updatedParkingSpace.getAddress());
        return parkingSpace;
    }

}
