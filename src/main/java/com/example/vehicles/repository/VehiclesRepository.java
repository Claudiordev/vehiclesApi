package com.example.vehicles.repository;

import com.example.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VehiclesRepository extends JpaRepository<Vehicle,String> {

    List<Vehicle> findAllById(String id);

    List<Vehicle> findAllByNameLike(String name);
}
