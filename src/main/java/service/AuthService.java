package service;

import controller.AdminController;
import controller.UserController;
import dto.Profile;
import enums.UserType;
import repository.AuthRepository;


public class AuthService {
    private final AuthRepository authRepository = new AuthRepository();
    private final AdminController adminController = new AdminController();
    private final UserController userController = new UserController();

    public void profileService(Profile profile) {
        if (!authRepository.checkPhone(profile.getPhone())) {
            if (authRepository.savaProfile(profile)) {
                System.out.println("Success.");
            }
        } else {
            System.out.println("Bunday nomerdan oldin royhatdan otgan!");
        }
    }

    public void login(String phone, String password) {
        if (password != null && phone != null) {
            Profile profile = authRepository.checkPhone(phone, password);
            if (profile.getType().equals(UserType.ADMIN)) {
                adminController.start(profile);
            } else if (profile.getType().equals(UserType.USER)) {
                userController.start(profile);
            }
        }

    }


}
