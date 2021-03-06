package com.softuni.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int age;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToMany
    private Set<RoleEntity> roles;
    @ManyToOne
    private BatEntity bat;
    @ManyToOne
    private GloveEntity glove;
    @ManyToOne
    private TeamEntity team;

    public UserEntity() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public BatEntity getBat() {
        return bat;
    }

    public void setBat(BatEntity bat) {
        this.bat = bat;
    }

    public GloveEntity getGlove() {
        return glove;
    }

    public void setGlove(GloveEntity glove) {
        this.glove = glove;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public void addRole(RoleEntity roleEntity){
        this.roles.add(roleEntity);
    }
}
