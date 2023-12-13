package repository;

import db.Database;
import dto.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRepository {

    public boolean createTestRepository(Test test) {
        try(Connection connection= Database.getConnection()){
            Statement statement=connection.createStatement();
            String sql = "insert into test(question,a,b,c,d,correctAnswer) values ('%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, test.getQuestion(), test.getA(),test.getB(),test.getC(),test.getD(),test.getCorrectAnswer());
           return statement.executeUpdate(sql)!=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
