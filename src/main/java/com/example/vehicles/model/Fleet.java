package com.example.vehicles.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "fleets", schema = "vehicles")
public class Fleet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "fleet")
    private Collection<Vehicle> vehiclesById;

    public Fleet(){

    }

    public Fleet(String name) {
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
        Fleet fleet = (Fleet) o;
        return id == fleet.id && Objects.equals(name, fleet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<Vehicle> getVehiclesById() {
        return vehiclesById;
    }

    public void setVehiclesById(Collection<Vehicle> vehiclesById) {
        this.vehiclesById = vehiclesById;
    }
}
