package com.example.vehicles.data;

import com.example.vehicles.model.*;
import com.example.vehicles.service.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Retrieve all mocked data and inject it/update database
 */
@Component
public class RetrieveData {
    @Autowired
    FleetService fleetService;

    @Autowired
    BrandService brandService;
    @Autowired
    ServiceVehiclesService serviceVehiclesService;

    @Autowired
    StatusService statusService;
    @Autowired
    ServiceService serviceService;

    @Autowired
    CountryService countryService;

    @Autowired
    ChassisSeriesService chassisSeriesService;
    @Autowired
    VehicleService vehicleService;

    public RetrieveData(){
    }

    /**
     * Upload data from mocked data onto database
     */
    public void uploadDataToDatabase(String url){
        ResponseEntity<String> response = new RestTemplate().getForEntity(url,String.class);
        JSONArray vehiclesArray = new JSONObject(response.getBody()).getJSONArray("vehicles");

        for(int i = 0; i < vehiclesArray.length(); i++) {
            JSONObject vehicleObject = vehiclesArray.getJSONObject(i);
            String vehicleId = vehicleObject.getString("id");
            String vehicleName = "";
            try {
                vehicleName = vehicleObject.getString("name");
            } catch (JSONException ignored) {}

            try {
                gatherInfo(vehicleId, vehicleName);
            } catch (HttpClientErrorException httpClientErrorException) {
                if (httpClientErrorException.getStatusCode().value() == 401){
                    if (!vehicleService.exists(vehicleId)) {
                        vehicleService.save(new Vehicle(vehicleId,vehicleName));
                    }
                }
            }

            gatherServices(vehicleId);
        }
    }

    /**
     * Gather all info from one vehicle
     * @param vehicleId
     * @param vehicleName
     */
    public void gatherInfo(String vehicleId,String vehicleName) throws HttpClientErrorException{
            ResponseEntity<String> infoResponse = new RestTemplate().getForEntity("http://localhost:1337/vehicle/info?id=" + vehicleId, String.class);
            JSONObject vehicleInfo = new JSONObject(infoResponse.getBody());

            Fleet fleet;
            Brand brand;
            Country country;
            ChassisSeries chassisSeries;
            Status engineStatus;

            if (!statusService.exists(vehicleInfo.getString("engineStatus"))) {
                engineStatus = statusService.save(new Status(vehicleInfo.getString("engineStatus")));
            } else {
                engineStatus = statusService.findByName(vehicleInfo.getString("engineStatus"));
            }

            if (!fleetService.exists(vehicleInfo.getString("fleet"))) {
                fleet = fleetService.save(new Fleet(vehicleInfo.getString("fleet")));
            } else {
                fleet = fleetService.findByName(vehicleInfo.getString("fleet"));
            }

            if (!brandService.exists(vehicleInfo.getString("brand"))) {
                brand = brandService.save(new Brand(vehicleInfo.getString("brand")));
            } else {
                brand = brandService.findByName(vehicleInfo.getString("brand"));
            }

            if (!countryService.exists(vehicleInfo.getString("countryOfOperation"))) {
                country = countryService.save(new Country(vehicleInfo.getString("countryOfOperation")));
            } else {
                country = countryService.findByName(vehicleInfo.getString("countryOfOperation"));
            }

            if (!chassisSeriesService.exists(vehicleInfo.getString("cassisSeries"))) {
                chassisSeries = chassisSeriesService.save(new ChassisSeries(vehicleInfo.getString("cassisSeries")));
            } else {
                chassisSeries = chassisSeriesService.findByName(vehicleInfo.getString("cassisSeries"));
            }

            if (!vehicleService.exists(vehicleId)) {
                vehicleService.save(new Vehicle(vehicleId,vehicleName,
                        vehicleInfo.getString("msidn"),
                        engineStatus,
                        fleet,
                        brand,
                        country,
                        vehicleInfo.getString("chassisNumber"),
                        chassisSeries));
            }
    }

    /**
     * Gather all services from one vehicle
     * @param vehicleId
     */
    public void gatherServices(String vehicleId) {
        try {
            ResponseEntity<String> servicesResponse = new RestTemplate().getForEntity("http://localhost:1337/vehicle/services?id=" + vehicleId, String.class);
            JSONObject vehicleServices = new JSONObject(servicesResponse.getBody());
            JSONArray vehicleServicesList = vehicleServices.has("services") ? vehicleServices.getJSONArray("services") : null;
            Vehicle vehicle = vehicleService.findById(vehicleId).get();

            Status communicationStatus;

            if (vehicleServices.has("communicationStatus") && !statusService.exists(vehicleServices.getString("communicationStatus"))) {
                communicationStatus = statusService.save(new Status(vehicleServices.getString("communicationStatus")));
            } else {
                communicationStatus = statusService.findByName(vehicleServices.getString("communicationStatus"));
            }

            vehicle.setCommunicationStatus(communicationStatus);
            vehicleService.save(vehicle);

            if (vehicleServices.has("communicationStatus") && vehicleServices.getString("communicationStatus").equals("ACTIVE")){
                Service service;
                Status servicesStatus;

                //Loop all services present in response
                for(int i = 0; i < vehicleServicesList.length();i++){
                    JSONObject serviceInfo = vehicleServicesList.getJSONObject(i);

                    if (!serviceService.exists(serviceInfo.getString("serviceName"))) {
                        service = serviceService.save(new Service(serviceInfo.getString("serviceName")));
                    } else {
                        service = serviceService.findByName(serviceInfo.getString("serviceName")).get();
                    }

                    if (!statusService.exists(serviceInfo.getString("status"))) {
                        servicesStatus = statusService.save(new Status(serviceInfo.getString("status")));
                    } else {
                        servicesStatus = statusService.findByName(serviceInfo.getString("status"));
                    }

                    String reason = serviceInfo.has("reason") ? serviceInfo.getString("reason") : "";

                    if(!serviceVehiclesService.hasService(vehicle,service)) {
                        ServicesVehicles servicesVehicles = new ServicesVehicles(service,vehicle,servicesStatus,reason);
                        serviceVehiclesService.save(servicesVehicles);
                    }
                }
            }

        } catch (HttpClientErrorException ignored){

        }
    }
}
