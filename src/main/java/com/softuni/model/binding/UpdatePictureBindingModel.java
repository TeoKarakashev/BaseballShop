package com.softuni.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UpdatePictureBindingModel {

    private MultipartFile imageUrl;

    public UpdatePictureBindingModel() {
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }
}
