package com.softuni.repository;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByRole(UserRole role);
}
