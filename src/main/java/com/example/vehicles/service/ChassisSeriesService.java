package com.example.vehicles.service;

import com.example.vehicles.model.ChassisSeries;
import com.example.vehicles.repository.ChassisSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassisSeriesService {

    @Autowired
    ChassisSeriesRepository chassisSeriesRepository;

    public boolean exists(String name) {
        return !chassisSeriesRepository.findAllByName(name).isEmpty();
    }

    public ChassisSeries save(ChassisSeries chassisSeries) {
        return chassisSeriesRepository.save(chassisSeries);
    }

    public ChassisSeries findByName(String name) {
        List<ChassisSeries> chassisSeriesList = chassisSeriesRepository.findAllByName(name);
        if (!chassisSeriesList.isEmpty()) {
            return chassisSeriesList.get(0);
        }

        return null;
    }
}
