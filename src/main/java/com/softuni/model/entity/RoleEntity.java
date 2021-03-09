package com.softuni.model.entity;

import com.softuni.model.entity.enums.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public RoleEntity() {
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
