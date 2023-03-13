package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int countDays = Integer.parseInt(reader.readLine());
            int[] menu = new int[countDays];
            for (int i = 0; i < countDays; i++) {
                menu[i] = Integer.parseInt(reader.readLine());
            }
            findResult(menu, countDays);
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static void findResult(int[] menu, int countDays){
        int[][] matrix = new int[countDays + 1][countDays + 1];
        for (int i = 0; i < countDays + 1; i++) {
            for (int j = 0; j < countDays + 1; j++) {
                matrix[i][j] = -1;
            }
        }

        matrix[0][0] = 0;

        for (int j = 0; j < countDays; j++) {
            for (int i = 0; i < countDays; i++) {
                if(matrix[i][j] > -1){
                    if(menu[j] > 100){
                        if(i > 0){
                            if(matrix[i - 1][j + 1] == -1 || matrix[i - 1][j + 1] > matrix[i][j]) {
                                matrix[i - 1][j + 1] = matrix[i][j];
                            }
                        }
                        if(matrix[i + 1][j + 1] == -1 || matrix[i + 1][j + 1] > matrix[i][j] + menu[j]) {
                            matrix[i + 1][j + 1] = matrix[i][j] + menu[j];
                        }
                    } else {
                        if(i > 0){
                            if(matrix[i - 1][j + 1] == -1 || matrix[i - 1][j + 1] > matrix[i][j]) {
                                matrix[i - 1][j + 1] = matrix[i][j];
                            }
                        }
                        if(matrix[i][j + 1] == -1 || matrix[i][j + 1] > matrix[i][j] + menu[j]) {
                            matrix[i][j + 1] = matrix[i][j] + menu[j];
                        }
                    }
                }
            }
        }
        int result = matrix[0][countDays];
        int countSavedCoupon = 0;
        for (int i = 1; i < countDays + 1; i++) {
            if(result == -1){
                result = matrix[i][countDays];
                countSavedCoupon = i;
            } else if(matrix[i][countDays] != -1 && matrix[i][countDays] <= result){
                result = matrix[i][countDays];
                countSavedCoupon = i;
            }
        }
        System.out.println(result);
        System.out.print(countSavedCoupon);
        List<Integer> freeDays = new ArrayList<>();

        while (countDays != 0){
            if(countSavedCoupon < countDays && matrix[countSavedCoupon + 1][countDays - 1] == matrix[countSavedCoupon][countDays]){
                freeDays.add(countDays);
                ++countSavedCoupon;
            } else if(countSavedCoupon != 0 && matrix[countSavedCoupon][countDays] - menu[countDays - 1] == matrix[countSavedCoupon - 1][countDays - 1] && matrix[countSavedCoupon][countDays] - menu[countDays - 1] != matrix[countSavedCoupon][countDays - 1]){
                --countSavedCoupon;
            }
            --countDays;
        }
        System.out.println(" " + freeDays.size());
        for (int i = freeDays.size() - 1; i > -1; i--) {
            System.out.print(freeDays.get(i) + " ");
        }
    }
}