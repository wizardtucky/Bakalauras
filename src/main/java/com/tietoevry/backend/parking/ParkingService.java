package com.tietoevry.backend.parking;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.tietoevry.backend.parking.model.ParkingSpace;
import com.tietoevry.backend.parking.model.ParkingSpaceDto;
import com.tietoevry.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final UserRepository userRepository; // prideti user prie parking space?

    public ParkingSpaceDto createParking(ParkingSpaceDto parkingSpace) {
        LocalDateTime now = LocalDateTime.now();
        ParkingSpace newParkingSpace = ParkingSpace.builder()
            .userId(parkingSpace.getUserId())
            .userWhoReserved(0L)
            .paidSpot(parkingSpace.isPaidSpot())
            .disabledSpot(parkingSpace.isDisabledSpot())
            .smallCarSpot(parkingSpace.isSmallCarSpot())
            .address(parkingSpace.getAddress())
            .latitude(parkingSpace.getLatitude())
            .longitude(parkingSpace.getLongitude())
            .creationTime(now)
            .build();
        parkingSpaceRepository.save(newParkingSpace);
        return ParkingMapper.toParkingSpaceDto(newParkingSpace);
    }

    public List<ParkingSpaceDto> getAllParkingSpaces(){
        deleteExpiredParkings();
        return parkingSpaceRepository
            .findAll()
            .stream()
            .map(ParkingMapper::toParkingSpaceDto)
            .collect(Collectors.toList());
    }

    public void deleteExpiredParkings(){
        List<ParkingSpaceDto> fullList = parkingSpaceRepository
            .findAll()
            .stream()
            .map(ParkingMapper::toParkingSpaceDto)
            .collect(Collectors.toList());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMines1Day = now.minusDays(1);
        for(final ParkingSpaceDto parking : fullList){
            if (parking.getCreationTime().isBefore(nowMines1Day)){
                deleteParkingSpace(parking.getId());
                System.out.println(parking);
            }
        }
    }

    public ParkingSpace getParkingSpace2(Long id){
        Optional<ParkingSpace> parkingSpace = parkingSpaceRepository.findById(id);
        parkingSpace.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ParkingSpace not found by id " + id));
        return parkingSpace.get();
    }

    public Optional<ParkingSpaceDto> getParkingSpaceDto(Long id){
        return parkingSpaceRepository.findById(id).map(ParkingMapper::toParkingSpaceDto);
    }

    public void deleteParkingSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }

    public ParkingSpaceDto updateParkingSpace(ParkingSpaceDto parkingSpaceDto) {
        ParkingSpace parkingSpace = getParkingSpace2(parkingSpaceDto.getId());
        parkingSpace = ParkingMapper.toUpdateParkingSpace(parkingSpaceDto, parkingSpace);
        parkingSpaceRepository.save(parkingSpace);
        return ParkingMapper.toParkingSpaceDto(parkingSpace); // tesiog parkingSpaceDto?
    }

    public ParkingSpaceDto updateParkingSpaceReserveState(Long id, Long reservedUserId) {
        ParkingSpace parkingSpace = getParkingSpace2(id);
        parkingSpace.setUserWhoReserved(reservedUserId);
        parkingSpaceRepository.save(parkingSpace);
        return ParkingMapper.toParkingSpaceDto(parkingSpace); // tesiog parkingSpaceDto?
    }
}
