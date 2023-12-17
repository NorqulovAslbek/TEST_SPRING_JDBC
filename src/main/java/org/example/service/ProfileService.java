package org.example.service;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.enums.UserType;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository ;
    @Autowired
    private  AdminController adminController ;
    @Autowired
    private  UserController userController ;


    public void profileService(Profile profile) {
        if (profileRepository.checkPhoneAndPassword(profile.getPhone())) {
            if (profileRepository.savaProfile(profile)) {
                System.out.println("Success.");
            }
        } else {
            System.out.println("Bunday nomerdan oldin royhatdan otgan!");
        }
    }

    public void login(String phone, String password) {
        if (password != null && phone != null) {
            Profile profile = profileRepository.checkPhoneAndPassword(phone, password);
            if (profile.getType().equals(UserType.ADMIN)) {
                adminController.start(profile);
            } else if (profile.getType().equals(UserType.USER)) {
                userController.start(profile);
            }
        }
    }


    public void usersStatisticsService() {
        List<Profile> profileList = profileRepository.getProfileList();
        profileList.sort((o1, o2) -> o2.getResult()- o1.getResult());
        System.out.println("===========>>>> STATISTIC <<<<==============");
        for (int i = 0; i < profileList.size(); i++) {
            System.out.println((i+1)+"."+profileList.get(i).getSurname()+" >>>>> "+profileList.get(i).getResult());
        }
    }
}
