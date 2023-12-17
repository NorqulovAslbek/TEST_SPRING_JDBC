package org.example.service;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.enums.UserType;
import org.example.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private  AuthRepository authRepository ;
    @Autowired
    private  AdminController adminController ;
    @Autowired
    private  UserController userController ;


    public void profileService(Profile profile) {
        if (authRepository.checkPhoneAndPassword(profile.getPhone())) {
            if (authRepository.savaProfile(profile)) {
                System.out.println("Success.");
            }
        } else {
            System.out.println("Bunday nomerdan oldin royhatdan otgan!");
        }
    }

    public void login(String phone, String password) {
        if (password != null && phone != null) {
            Profile profile = authRepository.checkPhoneAndPassword(phone, password);
            if (profile.getType().equals(UserType.ADMIN)) {
                adminController.start(profile);
            } else if (profile.getType().equals(UserType.USER)) {
                userController.start(profile);
            }
        }

    }


}
