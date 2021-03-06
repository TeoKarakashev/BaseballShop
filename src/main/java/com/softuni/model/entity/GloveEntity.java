package com.softuni.model.entity;

import com.softuni.model.entity.enums.GloveMaterial;
import com.softuni.model.entity.enums.GloveSize;

import javax.persistence.*;

@Entity
@Table(name = "gloves")
public class GloveEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Enumerated(EnumType.STRING)
    private GloveSize size;
    @Enumerated(EnumType.STRING)
    private GloveMaterial material;
    @Column(columnDefinition = "text")
    private String description;

    public GloveEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public GloveSize getSize() {
        return size;
    }

    public void setSize(GloveSize size) {
        this.size = size;
    }

    public GloveMaterial getMaterial() {
        return material;
    }

    public void setMaterial(GloveMaterial material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
