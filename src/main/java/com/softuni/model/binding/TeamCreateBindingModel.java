package com.softuni.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamCreateBindingModel {

    @Size(min = 1, message = "Name is required")
    private String name;
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;
    @NotNull(message = "Image must be chosen")
    private String imageUrl;
    @Size(min = 3, message = "Address must be at least 3 characters long")
    private String address;
    @Min(value = 0, message = "Capacity must be positive")
    private int capacity;

    public TeamCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
