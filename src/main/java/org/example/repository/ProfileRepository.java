package org.example.repository;

import org.example.dto.Profile;
import org.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository

public class ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean savaProfile(Profile profile) {
        String sql = "INSERT INTO profile_test(name,surname,phone,password,type,create_date,result) VALUES (?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, ps -> {
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getSurname());
            ps.setString(3, profile.getPhone());
            ps.setString(4, profile.getPassword());
            ps.setString(5, profile.getType().name());
            ps.setTimestamp(6, Timestamp.valueOf(profile.getCreate_date()));
            ps.setInt(7, profile.getResult());
        });
        return update != 0;
    }


    public boolean checkPhoneAndPassword(String phone) {
        String sql = "SELECT phone FROM profile_test WHERE phone=?";
        List<Profile> profile = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class), phone);
        return profile.isEmpty();
    }

    public Profile checkPhoneAndPassword(String phone, String password) {
        String sql = "SELECT * FROM profile_test WHERE phone=? AND password=?";
        List<Profile> profiles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class), phone, MD5.getMD5Password(password));
        return profiles.get(0);
    }

    public void updateProfileResult(Integer result, Integer id) {
        String sql = "UPDATE profile_test SET result=? WHERE id=?";
        jdbcTemplate.update(sql, result, id);
    }

    public List<Profile> getProfileList() {
        String sql = "SELECT *FROM profile_test";
        List<Profile> profiles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
        return profiles;
    }


}
