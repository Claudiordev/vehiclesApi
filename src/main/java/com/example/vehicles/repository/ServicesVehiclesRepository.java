package com.example.vehicles.repository;

import com.example.vehicles.model.Service;
import com.example.vehicles.model.ServicesVehicles;
import com.example.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesVehiclesRepository extends JpaRepository<ServicesVehicles,Long> {

    List<ServicesVehicles> findAllByVehicleAndService(Vehicle vehicle, Service service);

    List<ServicesVehicles> findByVehicleId(String id);

    List<ServicesVehicles> findByServiceNameAndServiceStatusName(String serviceName, String serviceStatus);
}
