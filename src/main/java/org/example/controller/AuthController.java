package org.example.controller;

import org.example.dto.Profile;
import org.example.enums.UserType;
import org.example.service.AuthService;
import org.example.util.GetAction;
import org.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class AuthController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private AuthService authService;
    @Autowired
    private GetAction getAction;



    public void start() {
        boolean n = true;
        while (n) {
            menu();
            switch (getAction.getAction()) {
                case 1 -> registration();
                case 2 -> login();
                case 0 -> n = false;
                default -> {
                    System.out.println("No tog'ri tanlov iltimos qaytadan kiriting!!");
                }

            }
        }

    }

    private void login() {
        System.out.print("Enter phone:");
        String phone = scanner.next();
        System.out.print("Enter password:");
        String password = scanner.next();
        authService.login(phone, password);

    }

    private void registration() {
        System.out.print("Enter name:");
        String name = scanner.next();
        System.out.print("Enter surname:");
        String surname = scanner.next();
        System.out.print("Enter phone:");
        String phone = scanner.next();
        System.out.print("Enter password:");
        String password = scanner.next();
        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(MD5.getMD5Password(password));
        profile.setType(UserType.USER);
        profile.setCreate_date(LocalDateTime.now());
        authService.profileService(profile);
    }


    private void menu() {
        System.out.print("**************Menu*************\n" + """
                1. Registration >
                2. Login>
                0. Exit>
                """);
    }



}
