package com.example.vehicles.service;

import com.example.vehicles.model.ServicesVehicles;
import com.example.vehicles.model.Vehicle;
import com.example.vehicles.repository.ServicesVehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVehiclesService {

    @Autowired
    ServicesVehiclesRepository servicesVehiclesRepository;

    public boolean hasService(Vehicle vehicle, com.example.vehicles.model.Service service) {
        return !servicesVehiclesRepository.findAllByVehicleAndService(vehicle,service).isEmpty();
    }

    public ServicesVehicles save(ServicesVehicles servicesVehicles){
        return servicesVehiclesRepository.save(servicesVehicles);
    }

    public List<ServicesVehicles> findByVehicleId(String id){
        return servicesVehiclesRepository.findByVehicleId(id);
    }

    public List<ServicesVehicles> findByServiceNameAndServiceStatus(String serviceName, String serviceStatus){
        return servicesVehiclesRepository.findByServiceNameAndServiceStatusName(serviceName,serviceStatus);
    }
}
