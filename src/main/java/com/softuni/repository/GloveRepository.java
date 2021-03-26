package com.softuni.repository;

import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.GloveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GloveRepository extends JpaRepository<GloveEntity, String> {

    List<GloveEntity> findByBrand(BrandEntity brand);

    Optional<GloveEntity> findByName(String name);
}
