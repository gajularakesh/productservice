package com.scalar.productservice.repository;

import com.scalar.productservice.models.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CatagoryRepository extends JpaRepository<Catagory, Long> {

    Catagory findByTitle(String catagoryTitle);

    List<Catagory> findAll();
}
