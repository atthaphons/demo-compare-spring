package com.example.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class TextFileServiceTest {

    private final TextFileService service = new TextFileService();
    private final String TEST_FILENAME = "junit-test-output.txt";

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }

    @Test
    void testGenerateFile() throws IOException {
        String content = "Hello-JUnit";
        service.generateFile(TEST_FILENAME, content);

        Path path = Paths.get(TEST_FILENAME);
        assertTrue(Files.exists(path), "File should exist");
        assertEquals(content, Files.readString(path), "Content should match");
    }
}
