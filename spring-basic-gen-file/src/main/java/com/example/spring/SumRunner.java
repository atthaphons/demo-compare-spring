package com.example.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.stream.Stream;

@Component
@Profile("sum") // จะทำงานเมื่อสั่งรันด้วย profile "sum" เท่านั้น
public class SumRunner implements CommandLineRunner {
    private final SumService sumService;

    public SumRunner(SumService sumService) {
        this.sumService = sumService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0)
            return;

        // กรองเอาเฉพาะตัวเลข ก่อนจะสั่ง parseInt
        var nums = Stream.of(args)
                .filter(s -> s.matches("-?\\d+")) // ตรวจสอบว่าเป็นตัวเลข (รองรับเลขลบด้วย)
                .map(Integer::parseInt)
                .toList();

        if (nums.isEmpty()) {
            System.out.println("ไม่พบตัวเลขใน Arguments กรุณาตรวจสอบการป้อนข้อมูล");
            return;
        }

        sumService.calculateAndSave(nums);
        System.exit(0);
    }
}