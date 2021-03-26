package com.softuni.model.binding;

import com.softuni.model.entity.enums.GloveMaterial;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class GloveCreateBindingModel {

    @Size(min = 1, message = "Name is required")
    private String name;
    @NotNull(message = "Image must be chosen")
    private MultipartFile imageUrl;
    @NotNull(message = "Material must be chosen")
    private GloveMaterial material;
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;
    @Min(value = 0, message = "Size must be positive")
    private Double size;
    @Min(value = 0, message = "Price must be positive")
    private BigDecimal price;
    @NotNull(message = "Brand must be chosen")
    private String brand;

    public GloveCreateBindingModel() {
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
