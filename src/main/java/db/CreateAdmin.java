package db;

import dto.Profile;
import enums.UserType;
import repository.AuthRepository;
import util.MD5;

import java.time.LocalDateTime;

public class CreateAdmin {
    private final static Profile profile = new Profile();
    private final static AuthRepository authRepository = new AuthRepository();

    public static void createAdmin() {
        profile.setName("Aslbek");
        profile.setSurname("Norqulov");
        profile.setPhone("+998991205577");
        profile.setPassword(MD5.getMD5Password("root123"));
        profile.setType(UserType.ADMIN);
        profile.setCreate_date(LocalDateTime.now());
        if(!authRepository.checkPhone(profile.getPhone())) {
            authRepository.savaProfile(profile);
        }
    }

}
