package com.example.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class SumService {
    @Value("${app.sum-path:sum_spring.txt}")
    private String sumPath;

    public void calculateAndSave(List<Integer> numbers) throws IOException {
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        try (FileWriter writer = new FileWriter(sumPath)) {
            writer.write("Spring Sum Result: " + sum);
            System.out.println("Spring Context Save: " + sum + " Success");
        }
    }
}