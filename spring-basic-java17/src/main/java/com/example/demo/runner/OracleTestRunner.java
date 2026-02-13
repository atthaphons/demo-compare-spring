package com.example.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OracleTestRunner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println("Checking Oracle Connection...");
        System.out.println("----------------------------------------");
        try {
            String sql = "SELECT 'Hello Oracle 19c - Connection Successful!' FROM DUAL";
            String result = jdbcTemplate.queryForObject(sql, String.class);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Oracle Connection Failed: " + e.getMessage());
            System.err.println("Please check your application.properties settings.");
        }
        System.out.println("----------------------------------------");
    }
}
