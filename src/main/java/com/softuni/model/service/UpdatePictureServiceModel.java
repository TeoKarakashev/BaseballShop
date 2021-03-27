package com.softuni.model.service;

import org.springframework.web.multipart.MultipartFile;

public class UpdatePictureServiceModel {

    private MultipartFile imageUrl;

    public UpdatePictureServiceModel() {
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }
}
