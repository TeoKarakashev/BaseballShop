package com.softuni.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


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

    public TeamEntity() {
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
}

