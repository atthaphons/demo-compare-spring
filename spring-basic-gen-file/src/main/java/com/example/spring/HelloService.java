package com.example.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class HelloService {

    // Spring จะดึงค่าจาก application.properties มาใส่ให้เอง
    @Value("${app.export-path}")
    private String exportPath;

    public void writeHello(String p1, String p2) throws IOException {
        // ใช้ try-with-resources เหมือนแบบ Manual เพื่อให้ไฟล์ไม่ว่างเปล่า
        try (FileWriter writer = new FileWriter(exportPath)) {
            writer.write("hello " + p1 + " " + p2);
            System.out.println("Spring Boot Context wrote to: " + exportPath);
        }
    }
}