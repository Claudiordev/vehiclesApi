package com.example.vehicles.repository;

import com.example.vehicles.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FleetRepository extends JpaRepository<Fleet,Integer> {

    List<Fleet> findAllByName(String name);
}
