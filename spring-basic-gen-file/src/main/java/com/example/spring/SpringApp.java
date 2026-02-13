package com.example.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List; // ต้องเพิ่ม
import java.util.stream.Stream; // ต้องเพิ่ม
import java.util.stream.Collectors; // สำหรับ Java version ต่ำกว่า 16

@SpringBootApplication
public class SpringApp implements CommandLineRunner {

    private final HelloService helloService;
    private final SumService sumService;

    public SpringApp(HelloService helloService, SumService sumService) {
        this.helloService = helloService;
        this.sumService = sumService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // --- ส่วนที่ 1: Logic สำหรับการบวกเลข ---
        if (args.length > 0 && args[0].matches("\\d+")) {
            // เปลี่ยนจาก args เป็น List ของตัวเลข ไม่เกิน 10 ตัว
            List<Integer> nums = Stream.of(args)
                    .filter(s -> s.matches("\\d+")) // กรองเอาเฉพาะตัวเลขป้องกัน Error
                    .map(Integer::parseInt)
                    .limit(10)
                    .toList(); // สำหรับ Java 16+ ถ้าเก่ากว่าใช้ .collect(Collectors.toList())

            sumService.calculateAndSave(nums);
            System.exit(0); // ทำงานเสร็จแล้วปิดโปรแกรมเลย
        }

        // --- ส่วนที่ 2: Logic สำหรับ Hello (ถ้าไม่ได้เข้ามาบวกเลข) ---
        if (args.length < 2) {
            System.err.println("Usage for Sum: java -jar app.jar <num1> <num2> ...");
            System.err.println("Usage for Hello: java -jar app.jar <name> <id>");
            System.exit(1);
        }

        try {
            helloService.writeHello(args[0], args[1]);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}