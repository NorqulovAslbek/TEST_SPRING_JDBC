package org.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Database {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void crateTable() {
        String profile = """
                CREATE TABLE IF NOT EXISTS profile_test(
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
        jdbcTemplate.execute(profile);
        jdbcTemplate.execute(test);
    }


}
