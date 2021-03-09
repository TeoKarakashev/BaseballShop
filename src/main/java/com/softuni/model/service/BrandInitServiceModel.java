package com.softuni.model.service;

import com.google.gson.annotations.Expose;

public class BrandInitServiceModel {

    @Expose
    private String name;
    @Expose
    private String description;

    public BrandInitServiceModel() {
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
}
