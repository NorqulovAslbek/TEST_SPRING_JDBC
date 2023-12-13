package repository;

import db.Database;
import dto.Profile;
import enums.UserType;
import util.MD5;

import java.sql.*;

public class AuthRepository {

    public boolean savaProfile(Profile profile) {
        try {
            Connection connection = Database.getConnection();
            String sql = "INSERT INTO profile(name,surname,phone,password,type,create_date,result) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getPhone());
            preparedStatement.setString(4, profile.getPassword());
            preparedStatement.setString(5, String.valueOf(profile.getType()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(profile.getCreate_date()));
            preparedStatement.setInt(7, profile.getResult());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    public boolean checkPhone(String phone) {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT phone FROM profile");
            while (rs.next()) {
                if (rs.getString("phone").equals(phone)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Profile checkPhone(String phone, String password) {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM profile");
            while (rs.next()) {

                if (rs.getString("phone").equals(phone) && rs.getString("password").equals(MD5.getMD5Password(password))) {
                    Profile profile = new Profile();
                    profile.setId(rs.getInt("id"));
                    profile.setName(rs.getString("name"));
                    profile.setSurname(rs.getString("surname"));
                    profile.setPhone(rs.getString("phone"));
                    profile.setPassword(rs.getString("password"));
                    profile.setType(UserType.valueOf(rs.getString("type")));
                    profile.setCreate_date(rs.getTimestamp("create_date").toLocalDateTime());
                    profile.setResult(rs.getInt("result"));
                    return profile;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
