package org.example.db;

import org.example.dto.Profile;
import org.example.enums.UserType;
import org.example.repository.AuthRepository;
import org.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class CreateAdmin {
    @Autowired
    private AuthRepository authRepository;

    public void createAdmin() {
        Profile profile = new Profile();
        profile.setName("Aslbek");
        profile.setSurname("Norqulov");
        profile.setPhone("7");
        profile.setPassword(MD5.getMD5Password("7"));
        profile.setType(UserType.ADMIN);
        profile.setCreate_date(LocalDateTime.now());
        if (authRepository.checkPhoneAndPassword(profile.getPhone())) {
            authRepository.savaProfile(profile);
        }
    }

}
