package com.services;

import com.models.Country;
import com.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryService implements ICountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }
}
