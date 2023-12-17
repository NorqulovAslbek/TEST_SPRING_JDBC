package org.example.repository;

import org.example.dto.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * createTestRepository()- Admin tomonidan kiritilgan testlarni
     * test bazasiga create qilish uchun ishlaydi.
     * @param test
     * @return
     */

    public boolean createTestRepository(Test test) {
        String sql = "insert into test(question,a,b,c,d,correctAnswer) values (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, test.getQuestion(), test.getA(), test.getB(), test.getC(), test.getD(), test.getCorrectAnswer()) != 0;
    }

    /**
     getTestListsRepository()- method bazadagi barcha malumotlarni
     Test objectlarini listi korinishiga otkazib return qiladi.
     **/
    public List<Test> getTestListsRepository() {
        String sql = "SELECT * FROM test";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Test.class));
    }




}
