package com.example.vehicles.service;

import com.example.vehicles.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    Optional<com.example.vehicles.model.Service> findById(int id){
        return serviceRepository.findById(id);
    }

    public boolean exists(String name){
        return !serviceRepository.findAllByName(name).isEmpty();
    }

    public com.example.vehicles.model.Service save(com.example.vehicles.model.Service service){
        return serviceRepository.save(service);
    }

    public Optional<com.example.vehicles.model.Service> findByName(String name){
        return serviceRepository.findByName(name);
    }
}
