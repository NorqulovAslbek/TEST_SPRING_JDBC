package controller;

import dto.Profile;
import util.Action;

public class UserController {

    public void start(Profile profile) {
        boolean u=true;
        while (u){
             menu();
             switch (Action.getAction()){
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
