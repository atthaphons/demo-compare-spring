package com.example.manual;

import java.io.FileWriter;
import java.io.IOException;

public class ManualApp {
    public static void main(String[] args) {
        // เช็ค Parameter
        if (args.length < 2) {
            System.err.println("Usage: java -jar app.jar <param1> <param2>");
            System.exit(1);
        }

        String p1 = args[0];
        String p2 = args[1];
        String path = "hello_manual.txt"; // Hardcoded หรืออ่านจาก System.getProperty

        try (FileWriter writer = new FileWriter(path)) {
            writer.write("hello " + p1 + " " + p2);
            // ไม่ต้องสั่ง writer.close() เองแล้ว พอกลางปีกกาปิด } ปุ๊บ มันจะ Save ให้ทันที
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // หลังจากพ้น block try-with-resources มาแล้ว ค่อยสั่ง Print และ Exit
        System.out.println("File written to: " + path);
        System.exit(0);
    }
}
