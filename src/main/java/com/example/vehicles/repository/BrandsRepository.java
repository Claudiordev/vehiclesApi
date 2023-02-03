package com.example.vehicles.repository;

import com.example.vehicles.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BrandsRepository extends JpaRepository<Brand,Integer> {

    List<Brand> findAllByName(String name);

    List<Brand> findAllByNameLike(String name);
}
