package com.example.vehicles.service;

import com.example.vehicles.model.Country;
import com.example.vehicles.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public boolean exists(String name) {
        return !countryRepository.findAllByName(name).isEmpty();
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public Country findByName(String name) {
        List<Country> countryList = countryRepository.findAllByName(name);
        if (!countryList.isEmpty()) {
            return countryList.get(0);
        }

        return null;
    }
}
