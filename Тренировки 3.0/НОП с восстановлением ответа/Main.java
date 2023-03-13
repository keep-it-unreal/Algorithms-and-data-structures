package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            List<Integer> firstList = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            reader.readLine();
            List<Integer> secondList = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            findResult(firstList, secondList);
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static void findResult(List<Integer> firstList, List<Integer> secondList){
        int[][] matrix = new int[secondList.size()][firstList.size()];

        boolean bool = false;
        for (int i = 0; i < firstList.size(); i++) {
            if(bool){
                matrix[0][i] = 1;
            }else if(Objects.equals(secondList.get(0), firstList.get(i))){
                matrix[0][i] = 1;
                bool = true;
            }
        }
        bool = false;
        for (int i = 0; i < secondList.size(); i++) {
            if(bool){
                matrix[i][0] = 1;
            }else if(Objects.equals(secondList.get(i), firstList.get(0))){
                matrix[i][0] = 1;
                bool = true;
            }
        }

        for (int i = 1; i < secondList.size(); i++) {
            for (int j = 1; j < firstList.size(); j++) {
                if(Objects.equals(secondList.get(i), firstList.get(j))){
                    matrix[i][j] = Math.max(matrix[i - 1][j], Math.max(matrix[i - 1][j - 1] + 1, matrix[i][j - 1]));
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], Math.max(matrix[i - 1][j - 1], matrix[i][j - 1]));
                }
            }
        }

        int firstFlag = firstList.size() - 1;
        int secondFlag = secondList.size() - 1;
        List<Integer> result = new ArrayList<>();

        if (matrix[secondFlag][firstFlag] != 0) {
            while (firstFlag != 0 && secondFlag != 0) {
                if (matrix[secondFlag][firstFlag] == matrix[secondFlag][firstFlag - 1]) {
                    --firstFlag;
                } else if (matrix[secondFlag][firstFlag] == matrix[secondFlag - 1][firstFlag]) {
                    --secondFlag;
                } else {
                    result.add(firstList.get(firstFlag));
                    --secondFlag;
                    --firstFlag;
                }
            }
            if (matrix[secondFlag][firstFlag] == 1) {
                if (secondFlag == 0) {
                    result.add(secondList.get(secondFlag));
                } else {
                    result.add(firstList.get(firstFlag));
                }
            }
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}