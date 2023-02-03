package com.example.vehicles.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "services_status", schema = "vehicles")
public class ServicesStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "serviceStatus")
    private Collection<ServicesVehicles> servicesVehiclesById;

    public ServicesStatus(){
    }

    public ServicesStatus(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesStatus that = (ServicesStatus) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<ServicesVehicles> getServicesVehiclesById() {
        return servicesVehiclesById;
    }

    public void setServicesVehiclesById(Collection<ServicesVehicles> servicesVehiclesById) {
        this.servicesVehiclesById = servicesVehiclesById;
    }
}
