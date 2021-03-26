package com.softuni.model.service;

import com.softuni.model.entity.enums.GloveMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class GloveServiceModel {


    private String name;

    private MultipartFile imageUrl;

    private GloveMaterial material;

    private String description;

    private Double size;

    private BigDecimal price;

    private String brand;

    public GloveServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
