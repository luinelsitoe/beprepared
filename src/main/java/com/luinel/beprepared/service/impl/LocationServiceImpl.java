package com.luinel.beprepared.service.impl;

import com.luinel.beprepared.model.City;
import com.luinel.beprepared.model.Province;
import com.luinel.beprepared.repository.CityRepository;
import com.luinel.beprepared.repository.ProvinceRepository;
import com.luinel.beprepared.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> getAllCitiesByProvinceId(Long provinceId) {
        return cityRepository.findByProvinceId(provinceId);
    }

    @Override
    public Province getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("A provincia não foi encontrada!"));
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(()->
                new EntityNotFoundException("O distrito não foi en;contrado!"));
    }
}
