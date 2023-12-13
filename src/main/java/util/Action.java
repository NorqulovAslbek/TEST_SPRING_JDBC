package util;

import java.util.Scanner;

public class Action {

    public static int getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number:");
        return scanner.nextInt();
    }
}
