import controller.AuthController;
import db.CreateAdmin;
import db.Database;

public class Main {
    public static void main(String[] args) {
        Database.crateTable();
        CreateAdmin.createAdmin();
        AuthController authController=new AuthController();
        authController.start();
    }
}
