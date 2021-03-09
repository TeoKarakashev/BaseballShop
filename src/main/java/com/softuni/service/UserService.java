package com.softuni.service;

import com.softuni.model.entity.UserEntity;
import com.softuni.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean userExists(String username);

    void initAdminUser();
}
