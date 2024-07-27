package com.luinel.beprepared.controller;

import com.luinel.beprepared.dto.response.CityRespondeDto;
import com.luinel.beprepared.dto.response.ProvinceResponseDto;
import com.luinel.beprepared.mapper.Mapper;
import com.luinel.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final Mapper mapper;
    private final LocationService locationService;
    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDto>> getAllProvinces() {
        return ResponseEntity.ok(mapper.mapToProvinceResponseDtoList(
                locationService.getAllProvinces())
        );
    }

    @GetMapping("/province")
    public ResponseEntity<ProvinceResponseDto> getProvinceById(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapToProvinceResponseDto(
                locationService.getProvinceById(id))
        );
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityRespondeDto>> getAllCities() {
        return ResponseEntity.ok(mapper.mapToCityRespondeDtoList(
                locationService.getAllCities())
        );
    }

    @GetMapping("/cities/{provinceId}")
    public ResponseEntity<List<CityRespondeDto>> getCityByProvinceId(@PathVariable Long provinceId) {
        return ResponseEntity.ok(mapper.mapToCityRespondeDtoList(
                locationService.getAllCitiesByProvinceId(provinceId)
        ));
    }

    @GetMapping("/city")
    public ResponseEntity<CityRespondeDto> getCityById(@RequestParam Long id) {
        return ResponseEntity.ok(mapper.mapToCityRespondeDto(
                locationService.getCityById(id)
        ));
    }
}
