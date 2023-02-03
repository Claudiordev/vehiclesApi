package com.example.vehicles.service;

import com.example.vehicles.model.Brand;
import com.example.vehicles.repository.BrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    BrandsRepository brandsRepository;

    public boolean exists(String name) {
        return !brandsRepository.findAllByName(name).isEmpty();
    }

    public Brand save(Brand brand) {
        return brandsRepository.save(brand);
    }

    public Brand findByName(String name) {
        List<Brand> brandList = brandsRepository.findAllByName(name);
        if (!brandList.isEmpty()) {
            return brandList.get(0);
        }

        return null;
    }
}
