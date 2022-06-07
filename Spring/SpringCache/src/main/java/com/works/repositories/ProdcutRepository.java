package com.works.repositories;

import com.works.entities.Prodcut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdcutRepository extends JpaRepository<Prodcut, Integer> {
}