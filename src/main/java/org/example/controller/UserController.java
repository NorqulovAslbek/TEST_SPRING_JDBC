package org.example.controller;

import org.example.dto.Profile;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserController {

    @Autowired
    private GetAction getAction;

    public void start(Profile profile) {
        boolean u=true;
        while (u){
             menu();
             switch (getAction.getAction()){
                 case 1->startTheTest();
                 case 2->statistics();
                 case 3->u=false;
                 default -> System.out.print("You have selected a number that does not exist!!");
             }
        }

    }

    private void statistics() {
    }

    private void startTheTest() {
    }

    private void menu() {
        System.out.print("""
                ************* USER MENU *************
                1.Start the test >
                2.Statistics >
                3.Exit >
                """);
    }

}
