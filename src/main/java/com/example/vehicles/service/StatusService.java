package com.example.vehicles.service;

import com.example.vehicles.model.Brand;
import com.example.vehicles.model.Status;
import com.example.vehicles.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public boolean exists(String name){
        return !statusRepository.findAllByName(name).isEmpty();
    }

    public Status save(Status status){
        return statusRepository.save(status);
    }

    public Status findByName(String name) {
        List<Status> statusList = statusRepository.findAllByName(name);
        if (!statusList.isEmpty()) {
            return statusList.get(0);
        }

        return null;
    }
}
