package com.softuni.repository;

import com.softuni.model.entity.GloveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GloveRepository extends JpaRepository<GloveEntity, String> {

    @Query("select g from GloveEntity g")
    List<GloveEntity> getALlGloves();
}
