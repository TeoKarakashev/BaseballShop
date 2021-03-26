package com.softuni.repository;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatRepository extends JpaRepository<BatEntity, String> {

    List<BatEntity> findByBrand(BrandEntity brand);

    Optional<BatEntity> findByName(String name);
}
