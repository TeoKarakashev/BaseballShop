package com.softuni.service;

import com.softuni.model.service.UserRegisterServiceModel;
import com.softuni.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean userExists(String username);

    void initAdminUser();

    UserServiceModel findByUsername(String name);

    List<String> findAllUsers();

    void promoteUserToAdmin(String username);

    void save(UserServiceModel userEntity);
}
