package controller;

import dto.Profile;
import dto.Test;
import service.AdminTestService;
import util.Action;

import java.util.Scanner;

public class AdminController {

    private final Scanner scanner = new Scanner(System.in);
    private final AdminTestService adminTestService=new AdminTestService();

    public void start(Profile profile) {
        boolean n = true;
        while (n) {
            menu();
            switch (Action.getAction()) {
                case 1 -> addTest();
                case 2 -> changeTest();
                case 3 -> deleteTest();
                case 4 -> startTheTest();
                case 5 -> userStatistics();
                case 0 -> n = false;
                default -> System.out.println("You have selected a number that does not exist!!");
            }

        }


    }

    private void userStatistics() {

    }

    private void startTheTest() {

    }

    private void deleteTest() {

    }


    private void changeTest() {

    }

    private void addTest() {
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
        while (true) {
            System.out.print("Enter the correct answer:");
            String correctAnswer = scanner.nextLine();
            if (correctAnswer.equals("a") || correctAnswer.equals("b") || correctAnswer.equals("c") || correctAnswer.equals("d")) {
                test.setCorrectAnswer(correctAnswer);
                adminTestService.createTestService(test);
                return;
            }else {
                System.out.println("Error. Please try again!");
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
