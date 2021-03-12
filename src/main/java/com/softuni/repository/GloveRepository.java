package com.softuni.repository;

import com.softuni.model.entity.GloveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GloveRepository extends JpaRepository<GloveEntity, String> {
}
