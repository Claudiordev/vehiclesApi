package com.example.vehicles.repository;

import com.example.vehicles.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Integer> {

    List<Status> findAllByName(String name);
}
