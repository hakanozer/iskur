package com.works.repostories;

import com.works.entities.Productx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Productx, Integer> {

    List<Productx> findByTitleContains(@Param("title") String title);

}
