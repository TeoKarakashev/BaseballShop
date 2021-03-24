package com.softuni.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "teams")
public class TeamEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private LocalDate created;
    @Column(columnDefinition = "text")
    private String description;
    @Column(name = "image_url",length = 512)
    private String imageUrl;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int capacity;
    @ManyToOne
    private UserEntity creator;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<UserEntity> players;

    public TeamEntity() {
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<UserEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserEntity> players) {
        this.players = players;
    }

    public void addPlayer(UserEntity player){
        this.players.add(player);
    }
}

