package com.example.manual;

import java.io.FileWriter;
import java.io.IOException;

public class SumApp {
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 10) {
            System.err.println("กรุณาใส่ตัวเลข 1 - 10 ตัว");
            System.exit(1);
        }

        int sum = 0;
        try {
            for (String arg : args) {
                sum += Integer.parseInt(arg);
            }

            String path = "sum_manual.txt";
            try (FileWriter writer = new FileWriter(path)) {
                writer.write("SUM: " + sum);
                System.out.println("SAVE " + sum + " IN " + path + " SUCCESS");
            }
        } catch (NumberFormatException e) {
            System.err.println("Number Only");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}