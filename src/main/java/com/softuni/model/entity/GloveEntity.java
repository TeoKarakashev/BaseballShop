package com.softuni.model.entity;

import com.softuni.model.entity.enums.GloveMaterial;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gloves")
public class GloveEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private GloveMaterial material;
    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private Double size;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    private BrandEntity brand;


    public GloveEntity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
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


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
