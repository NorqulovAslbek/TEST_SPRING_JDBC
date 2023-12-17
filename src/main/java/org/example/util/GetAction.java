package org.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class GetAction {
    @Autowired
    private Scanner scannerInt;
    public int getAction() {
        System.out.print("Enter number:");
        return scannerInt.nextInt();
    }
}
