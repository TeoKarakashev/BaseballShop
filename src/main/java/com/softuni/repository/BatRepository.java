package com.softuni.repository;

import com.softuni.model.entity.BatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatRepository extends JpaRepository<BatEntity, String> {

}
