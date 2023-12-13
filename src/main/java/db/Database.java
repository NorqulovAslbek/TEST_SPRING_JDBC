package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "jdbc_test", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crateTable() {
        String profile = """
                CREATE TABLE IF NOT EXISTS profile(
                id SERIAL PRIMARY KEY,
                name VARCHAR(15) NOT NULL,
                surname VARCHAR(15) NOT NULL,
                phone VARCHAR(30) UNIQUE,
                password VARCHAR(50) NOT NULL,
                type VARCHAR(10) NOT NULL,
                create_date TIMESTAMP NOT NULL,
                result INTEGER NOT NULL);""";

        String test = """
                CREATE TABLE IF NOT EXISTS test(
                id SERIAL PRIMARY KEY,
                question TEXT NOT NULL,
                a TEXT NOT NULL,
                b TEXT NOT NULL,
                c TEXT NOT NULL,
                d TEXT NOT NULL,
                correctAnswer TEXT NOT NULL);""";
        execute(profile);
        execute(test);


    }

    private static void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
