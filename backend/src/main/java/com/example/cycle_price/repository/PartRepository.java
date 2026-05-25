package com.example.cycle_price.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cycle_price.entity.Part;

public interface PartRepository extends JpaRepository<Part, Long>{

    Optional<Part> findByName(String partName);

}
