package com.softuni.repository;

import com.softuni.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends JpaRepository<LogEntity, String> {
}
