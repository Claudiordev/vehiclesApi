package com.example.vehicles.repository;

import com.example.vehicles.model.ChassisSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChassisSeriesRepository extends JpaRepository<ChassisSeries,Integer> {
    List<ChassisSeries> findAllByName(String name);
}
