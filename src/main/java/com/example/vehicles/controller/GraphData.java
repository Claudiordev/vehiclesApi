package com.example.vehicles.controller;

import com.example.vehicles.data.RetrieveData;
import com.example.vehicles.model.ServicesVehicles;
import com.example.vehicles.model.Vehicle;
import com.example.vehicles.service.ServiceVehiclesService;
import com.example.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class GraphData {
    @Autowired
    VehicleService vehicleService;

    @Autowired
    ServiceVehiclesService serviceVehiclesService;

    @Autowired
    RetrieveData retrieveData;

    @PostConstruct
    public void loadGraphSchema() {
        //Load data onto
        retrieveData.uploadDataToDatabase("http://localhost:1337/vehicle/list");
    }


    @QueryMapping
    Iterable<Vehicle> vehicles() {
        return vehicleService.findAll();
    }

    @QueryMapping
    Vehicle vehicleById(@Argument String id){
        if (vehicleService.findById(id).isPresent()) {
            return vehicleService.findById(id).get();
        }

        return null;
    }

    @QueryMapping
    List<Vehicle> vehicleByName(@Argument String name) {
        return vehicleService.findByNamePartial(name);
    }

    @QueryMapping
    List<ServicesVehicles> vehicleByServiceNameAndServiceStatus(@Argument String serviceName, @Argument String serviceStatus){
        return serviceVehiclesService.findByServiceNameAndServiceStatus(serviceName,serviceStatus);
    }
}
