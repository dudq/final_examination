package com.services;

import com.models.City;

import java.util.List;

public interface ICityService {
    Iterable<City> findAll();

    City findById(Long id);

    void save(City city);

    void delete(Long id);
}
