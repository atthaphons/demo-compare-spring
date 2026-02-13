package com.example.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List; // เพิ่มตัวนี้
import java.util.stream.Stream; // เพิ่มตัวนี้

@Component
@Profile("hello") // จะทำงานเมื่อสั่งรันด้วย profile "hello" เท่านั้น
public class HelloRunner implements CommandLineRunner {
    private final HelloService helloService;

    public HelloRunner(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> cleanArgs = Stream.of(args)
                .filter(arg -> !arg.startsWith("--"))
                .toList();

        if (cleanArgs.size() < 2) {
            System.out.println("กรุณาใส่ชื่อ และ ID (เช่น Somchai Delivery001)");
            return;
        }

        // ใช้ค่าจาก cleanArgs แทน args ปกติ
        String name = cleanArgs.get(0);
        String id = cleanArgs.get(1);

        helloService.writeHello(name, id);
        System.exit(0);
    }
}