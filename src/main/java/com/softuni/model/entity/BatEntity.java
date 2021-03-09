package com.softuni.model.entity;

import com.softuni.model.entity.enums.BatMaterial;

import javax.persistence.*;

@Entity
@Table(name = "bats")
public class BatEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private BatMaterial material;
    @Column(nullable = false)
    private int weight;
    @Column(nullable = false)
    private int size;
    @ManyToOne
    private BrandEntity brand;

    public BatEntity() {
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BatMaterial getMaterial() {
        return material;
    }

    public void setMaterial(BatMaterial material) {
        this.material = material;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
