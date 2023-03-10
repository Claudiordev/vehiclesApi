package com.example.vehicles.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "services", schema = "vehicles")
public class Service {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "service")
    private Collection<ServicesVehicles> serviceDetails;

    public Service(){
    }

    public Service(String name){
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
        Service service = (Service) o;
        return id == service.id && Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<ServicesVehicles> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(Collection<ServicesVehicles> servicesVehiclesById) {
        this.serviceDetails = servicesVehiclesById;
    }
}
