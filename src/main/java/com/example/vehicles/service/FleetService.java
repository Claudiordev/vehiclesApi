package com.example.vehicles.service;

import com.example.vehicles.model.Fleet;
import com.example.vehicles.repository.FleetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleetService {

    @Autowired
    private FleetRepository fleetRepository;

    public boolean exists(String name) {
        return !fleetRepository.findAllByName(name).isEmpty();
    }

    public Fleet save(Fleet fleet) {
        return fleetRepository.save(fleet);
    }

    public Fleet findByName(String name) {
        List<Fleet> fleetList = fleetRepository.findAllByName(name);
        if (!fleetList.isEmpty()) {
            return fleetList.get(0);
        }

        return null;
    }
}
