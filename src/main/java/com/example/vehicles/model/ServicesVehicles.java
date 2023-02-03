package com.example.vehicles.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "services_vehicles", schema = "vehicles")
public class ServicesVehicles {
    @Basic
    @Column(name = "reason", nullable = true, length = 255)
    private String reason;
    @Basic
    @Column(name = "last_update", nullable = true)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "services_id", referencedColumnName = "id", nullable = false)
    private Service service;
    @ManyToOne
    @JoinColumn(name = "service_status_id", referencedColumnName = "id", nullable = false)
    private ServicesStatus serviceStatus;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id",nullable = false)
    private Vehicle vehicle;
    @GeneratedValue
    @Id
    private Long id;
    public ServicesVehicles(){
    }

    public ServicesVehicles(Service service,Vehicle vehicle,ServicesStatus servicesStatus, String reason){
        this.service = service;
        this.vehicle = vehicle;
        this.serviceStatus = servicesStatus;
        this.reason = reason;
        //this.lastUpdate = (Timestamp) new Date();
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesVehicles that = (ServicesVehicles) o;
        return Objects.equals(reason, that.reason) && Objects.equals(lastUpdate, that.lastUpdate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(reason, lastUpdate);
    }
    public Service getService() {
        return service;
    }
    public void setService(Service servicesByServicesId) {
        this.service = servicesByServicesId;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public ServicesStatus getServiceStatus() {
        return serviceStatus;
    }
    public void setServiceStatus(ServicesStatus servicesStatusByServiceStatusId) {
        this.serviceStatus = servicesStatusByServiceStatusId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
