package org.example.controller;

import org.example.dto.Profile;
import org.example.service.ProfileService;
import org.example.service.TestService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private TestService testService;

    @Autowired
    private GetAction getAction;

    public void start(Profile profile) {
        boolean u = true;
        while (u) {
            menu();
            switch (getAction.getAction()) {
                case 1 -> startTheTest(profile);
                case 2 -> statistics();
                case 0 -> u = false;
                default -> System.out.print("You have selected a number that does not exist!!");
            }
        }
    }

    private void statistics() {
        profileService.usersStatisticsService();
    }

    private void startTheTest(Profile profile) {
        testService.startTheTestService(profile);
    }

    private void menu() {
        System.out.println("""
                ************* USER MENU *************
                1.Start the test >
                2.Statistics >
                0.Exit >""");
    }

}
