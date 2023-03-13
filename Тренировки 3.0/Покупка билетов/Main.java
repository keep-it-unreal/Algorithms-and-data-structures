package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int countVisitors = Integer.parseInt(reader.readLine());
            String[] array;
            int[][] matrix = new int[countVisitors][3];
            for (int i = 0; i < countVisitors; i++) {
                array = reader.readLine().split(" ");
                matrix[i][0] = Integer.parseInt(array[0]);
                matrix[i][1] = Integer.parseInt(array[1]);
                matrix[i][2] = Integer.parseInt(array[2]);
            }
            System.out.println(minTime(matrix, countVisitors));
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static int minTime(int[][]matrix, int countVisitors) {
        int[] array = new int[countVisitors];
        array[0] = matrix[0][0];
        if (countVisitors == 1) {
            return array[0];
        }
        array[1] = Math.min(matrix[0][1], matrix[0][0] + matrix[1][0]);
        if (countVisitors == 2) {
            return array[1];
        }
        array[2] = Math.min(Math.min(matrix[0][0] + matrix[1][0] + matrix[2][0], matrix[0][2]), Math.min(matrix[0][1] + matrix[2][0], matrix[0][0] + matrix[1][1]));
        if (countVisitors == 3) {
            return array[2];
        }

        for (int i = 3; i < countVisitors; i++) {
            array[i] = Math.min(Math.min(matrix[i - 2][0] + matrix[i - 1][0] + matrix[i][0] + array[i - 3], matrix[i - 1][1] + array[i - 2]), Math.min(matrix[i - 2][2] + array[i - 3], matrix[i][0] + array[i - 1]));
        }
        return array[array.length - 1];
    }
}