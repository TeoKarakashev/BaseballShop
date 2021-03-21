package com.softuni.repository;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByRolesIn(Set<RoleEntity> roles);
}
