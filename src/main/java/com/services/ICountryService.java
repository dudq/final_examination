package com.services;

import com.models.Country;

import java.util.List;

public interface ICountryService {
    Iterable<Country> findAll();

    Country findById(Long id);

    void save(Country country);

    void delete(Long id);
}
