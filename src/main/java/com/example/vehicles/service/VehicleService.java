package com.example.vehicles.service;

import com.example.vehicles.model.Vehicle;
import com.example.vehicles.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehiclesRepository vehiclesRepository;

    public boolean exists(String id) {
        return !vehiclesRepository.findAllById(id).isEmpty();
    }

    public void save(Vehicle vehicles) {
        vehiclesRepository.save(vehicles);
    }

    public Iterable<Vehicle> findAll(){
        return vehiclesRepository.findAll();
    }

    public Optional<Vehicle> findById(String id){
        return vehiclesRepository.findById(id);
    }

    public List<Vehicle> findByNamePartial(String searchName) {
        return vehiclesRepository.findAllByNameLike("%" + searchName + "%");
    }
}
