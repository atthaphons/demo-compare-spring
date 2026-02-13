package com.example.demo.runner;

import com.example.demo.service.TextFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileGenerationRunner implements CommandLineRunner {

    private final TextFileService textFileService;

    public FileGenerationRunner(TextFileService textFileService) {
        this.textFileService = textFileService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting file generation process...");
        String filename = "sample-output.txt";
        String customMessage = (args.length > 0) ? args[0]
                : "Hello! This is a generated text file from Spring Boot Application.";

        String content = customMessage + "\nTimestamp: " + java.time.LocalDateTime.now();

        textFileService.generateFile(filename, content);
    }
}
