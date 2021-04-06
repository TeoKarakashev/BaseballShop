package com.softuni.model.entity;

import com.softuni.model.entity.enums.BatMaterial;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bats")
public class BatEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private BatMaterial material;
    @Column(nullable = false)
    private int weight;
    @Column(nullable = false)
    private int size;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private BigDecimal price;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    private BrandEntity brand;

    public BatEntity() {
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
