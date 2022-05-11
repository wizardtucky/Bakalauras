package com.tietoevry.backend.parking;

import com.tietoevry.backend.map_api.MapApiService;
import com.tietoevry.backend.map_api.model.MapLocationDto;
import com.tietoevry.backend.parking.model.ParkingSpace;
import com.tietoevry.backend.parking.model.ParkingSpaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ParkingController
{
    private final ParkingService parkingService;
    private final MapApiService mapApiService;

    @PostMapping
    public ParkingSpaceDto createParking(@RequestBody ParkingSpaceDto parkingSpace){
        return parkingService.createParking(parkingSpace);
    }

    @GetMapping
    public List<ParkingSpaceDto> getAllParkingSpaces() { return parkingService.getAllParkingSpaces();}
// NEAISKUS
    @GetMapping(path = "/by/{id}")
    public ParkingSpaceDto getParkingSpace(@PathVariable Long id){
        return parkingService.getParkingSpaceDto(id).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found by id " + id));
    }

    @GetMapping(path = "/exp/{id}")
    public ParkingSpace getParkingSpace2(@PathVariable Long id){
        return parkingService.getParkingSpace2(id);
    }
    // NEAISKUS
    @DeleteMapping(path = "/{id}")
    public void deleteParkingSpace(@PathVariable Long id){ parkingService.deleteParkingSpace(id);}

    @PutMapping
    public ParkingSpaceDto updateReservation(@Valid @RequestBody ParkingSpaceDto parkingSpaceDto){
        return parkingService.updateParkingSpace(parkingSpaceDto);
    }
    @PutMapping(path = "/{id}/{reservedUserId}")
    public ParkingSpaceDto updateReservationState(@Valid @PathVariable Long id, @PathVariable Long reservedUserId){
        return parkingService.updateParkingSpaceReserveState(id, reservedUserId);
    }

    @GetMapping(path = "/get-coordinates")
    public List<MapLocationDto> getMap(@RequestParam String location, @RequestParam String address) {
        return mapApiService.getCoordinates(location, address);
    }
}
