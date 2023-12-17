package org.example.controller;

import org.example.dto.Profile;
import org.example.dto.Test;
import org.example.service.ProfileService;
import org.example.service.TestService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class AdminController {
    @Autowired
    @Qualifier("scannerLine")
    private Scanner scanner;
    @Autowired
    private TestService testService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private GetAction getAction;

    public void start(Profile profile) {
        boolean n = true;
        while (n) {
            menu();
            switch (getAction.getAction()) {
                case 1 -> addTest();
                case 2 -> changeTest();
                case 3 -> deleteTest(profile);
                case 4 -> startTheTest(profile);
                case 5 -> userStatistics();
                case 0 -> n = false;
                default -> System.out.println("You have selected a number that does not exist!!");
            }
        }
    }

    private void userStatistics() {
       profileService.usersStatisticsService();
    }

    private void startTheTest(Profile profile) {
        testService.startTheTestService(profile);
    }

    private void deleteTest(Profile profile) {
        testService.deleteTestService(profile);
    }


    private void changeTest() {
        testService.changeTestService();
    }

    private void addTest() {
        boolean k = true;
        while (k) {
            Test test = new Test();
            System.out.print("Enter the question:");
            String question = scanner.nextLine();
            test.setQuestion(question);
            System.out.print("a)");
            String a = scanner.nextLine();
            test.setA(a);
            System.out.print("b)");
            String b = scanner.nextLine();
            test.setB(b);
            System.out.print("c)");
            String c = scanner.nextLine();
            test.setC(c);
            System.out.print("d)");
            String d = scanner.nextLine();
            test.setD(d);
            System.out.print("Enter the correct answer:");
            String answer = scanner.nextLine();
            String correctAnswer = switch (answer) {
                case "a" -> a;
                case "b" -> b;
                case "c" -> c;
                case "d" -> d;
                default -> throw new IllegalStateException("Unexpected value: " + answer);
            };
            test.setCorrectAnswer(correctAnswer);
            List<Test> list = new ArrayList<>();
            list.add(test);
            System.out.print("""
                    0-> stop>
                    1-> next>
                    enter:""");
            String stopOrNext = scanner.nextLine();
            if (stopOrNext.equals("0")) {
                testService.createTestService(list);
                k = false;
            }
        }

    }

    private void menu() {
        System.out.print("""
                ************* ADMIN MENU *************
                1.Add test >
                2.Change the test >
                3.Delete the test >
                4.Start the test >
                5.User statistics >
                0.Exit >
                """);
    }


}
