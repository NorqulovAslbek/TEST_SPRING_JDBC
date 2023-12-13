package controller;

import dto.Profile;
import enums.UserType;
import service.AuthService;
import util.Action;
import util.MD5;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AuthController {
    private final Scanner scanner = new Scanner(System.in);
    private final Profile profile = new Profile();
    private final AuthService authService = new AuthService();


    public void start() {
        boolean n = true;
        while (n) {
            menu();
            switch (Action.getAction()) {
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
        authService.login(phone,password);


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
