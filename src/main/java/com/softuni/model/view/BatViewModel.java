package com.softuni.model.view;

import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.enums.BatMaterial;

public class BatViewModel {

    private String id;
    private String name;
    private BatMaterial material;
    private int weight;
    private int size;
    private BrandEntity brand;


    public BatViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
