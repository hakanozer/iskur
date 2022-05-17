package com.works.repostories;


import com.works.entities.Productx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Productx, Integer> {
}
