package org.example;

import org.example.controller.AuthController;
import org.example.db.CreateAdmin;
import org.example.db.Database;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Database database = (Database) context.getBean("database");
        database.crateTable();
        CreateAdmin createAdmin=(CreateAdmin) context.getBean("createAdmin");
        createAdmin.createAdmin();
        AuthController authController=(AuthController)  context.getBean("authController");
        authController.start();
    }


}
