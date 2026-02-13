package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OracleConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testOracleSelect() {
        // Simple query to verify connection and data retrieval from Oracle 19c
        String sql = "SELECT MAX(NAME) FROM PRODUCT";

        String result = jdbcTemplate.queryForObject(sql, String.class);

        assertThat(result).isEqualTo("Mouse");
        System.out.println("Test Select Result: " + result);
    }
}
