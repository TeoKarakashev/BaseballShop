package com.softuni.repository;

import com.softuni.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {

    Optional<TeamEntity> findByName(String name);
}
