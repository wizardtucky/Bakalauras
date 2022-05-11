package com.tietoevry.backend.map_api;

import com.tietoevry.backend.map_api.model.MapLocation;
import com.tietoevry.backend.map_api.model.MapLocationData;
import com.tietoevry.backend.map_api.model.MapLocationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MapApiService {
    private String uri = "http://api.positionstack.com/v1/forward?access_key=c62624264ffe395099556b0951ba9edc&query=";
    //private String uri = "https://nominatim.openstreetmap.org/search?q=<query>";


    public List<MapLocationDto> getCoordinates(String location, String address){

        String request = uri + location + ", " + address;
        MapLocationData mapLocationData;

        RestTemplate restTemplate = new RestTemplate();
        mapLocationData = restTemplate.getForObject(request, MapLocationData.class);

        System.out.println(mapLocationData.toString());

        // mapLocationData.setData(filterList(mapLocationData.getData().get()));

        List<MapLocationDto> list = mapLocationData.getData().get().stream()
            .map(MapLocationMapper::ToMapLocationDto)
            .collect(Collectors.toList());

        System.out.println(list);

        return list;
    }

    private Optional<List<MapLocation>> filterList(List<MapLocation> mapLocationList){

       List<MapLocation> filteredData = new ArrayList<>();

        for(MapLocation mapLocation: mapLocationList){
            if(mapLocation.getStreet() != null)
                if(mapLocation.getCountry_code() != null)
                    if(mapLocation.getCountry_code().equals("LTU"))
                        filteredData.add(mapLocation);
        }

        return  Optional.of(filteredData);
    }
}
