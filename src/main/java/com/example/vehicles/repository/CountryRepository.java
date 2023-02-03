package com.example.vehicles.repository;

import com.example.vehicles.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    List<Country> findAllByName(String name);
}
