package com.softuni.model.service;

import com.softuni.model.entity.enums.BatMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class BatServiceModel {


    private String name;
    private MultipartFile imageUrl;
    private BatMaterial material;
    private String description;
    private int weight;
    private int size;
    private BigDecimal price;
    private String brand;

    public BatServiceModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
