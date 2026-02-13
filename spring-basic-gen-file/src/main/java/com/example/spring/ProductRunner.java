package com.example.spring;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import java.io.FileWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate; // เพิ่ม import   

@Component
@Profile("product")
public class ProductRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public ProductRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT MAX(NAME) FROM PRODUCT";

        // Spring ช่วยดึงค่าออกมาให้ในบรรทัดเดียว!
        String maxName = jdbcTemplate.queryForObject(sql, String.class);

        try (FileWriter writer = new FileWriter("product_spring.txt")) {
            writer.write("Max Product Name: " + maxName);
        }

        System.out.println("Spring Boot Data Saved: " + maxName);
        System.exit(0);
    }
}