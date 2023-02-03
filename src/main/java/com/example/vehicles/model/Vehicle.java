package com.example.vehicles.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicles", schema = "vehicles")
public class Vehicle {
    @Id
    @Column(name = "id", nullable = true, length = 36, unique = true)
    private String id;
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "msidn", nullable = false, length = 50)
    private String msidn;
    @Basic
    @Column(name = "chassis_number", nullable = false, length = 17)
    private String chassisNumber;
    @Basic
    @Column(name = "communication_status", nullable = true)
    private Byte communicationStatus;
    @ManyToOne
    @JoinColumn(name = "engine_status_id", referencedColumnName = "id", nullable = false)
    private EngineStatus engineStatus;
    @ManyToOne
    @JoinColumn(name = "fleet_id", referencedColumnName = "id", nullable = false)
    private Fleet fleet;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "chassis_series_id", referencedColumnName = "id", nullable = false)
    private ChassisSeries chassisSeries;

    @ManyToMany
    @JoinTable(name = "services_vehicles",joinColumns = @JoinColumn(name="vehicle_id"),inverseJoinColumns = @JoinColumn(name="services_id"))
    private Collection<Service> services;

    public Vehicle(){

    }
    public Vehicle(String id, String name, String msidn, EngineStatus engineStatus, Fleet fleet, Brand brand, Country country, String chassisNumber, ChassisSeries chassisSeries, boolean communicationStatus) {
        this.id = id;
        this.name = name;
        this.msidn = msidn;
        this.engineStatus = engineStatus;
        this.fleet = fleet;
        this.brand = brand;
        this.country = country;
        this.chassisNumber = chassisNumber;
        this.chassisSeries = chassisSeries;
        this.communicationStatus = (communicationStatus) ? (byte) 1 : (byte)0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsidn() {
        return msidn;
    }

    public void setMsidn(String msidn) {
        this.msidn = msidn;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Byte getCommunicationStatus() {
        return communicationStatus;
    }

    public void setCommunicationStatus(Byte communicationStatus) {
        this.communicationStatus = communicationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(name, vehicle.name) && Objects.equals(msidn, vehicle.msidn) && Objects.equals(chassisNumber, vehicle.chassisNumber) && Objects.equals(communicationStatus, vehicle.communicationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, msidn, chassisNumber, communicationStatus);
    }

    public EngineStatus getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(EngineStatus engineStatusByEngineStatusId) {
        this.engineStatus = engineStatusByEngineStatusId;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleetsByFleetId) {
        this.fleet = fleetsByFleetId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brandsByBrandId) {
        this.brand = brandsByBrandId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countriesByCountryId) {
        this.country = countriesByCountryId;
    }

    public ChassisSeries getChassisSeries() {
        return chassisSeries;
    }

    public void setChassisSeries(ChassisSeries chassisSeriesByChassisSeriesId) {
        this.chassisSeries = chassisSeriesByChassisSeriesId;
    }
}
