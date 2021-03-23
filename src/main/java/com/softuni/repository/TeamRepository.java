package com.softuni.repository;

import com.softuni.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {

}
