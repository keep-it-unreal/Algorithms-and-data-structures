package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String[] matrixSize = reader.readLine().split(" ");
            int countRows = Integer.parseInt(matrixSize[0]);
            int countColumn = Integer.parseInt(matrixSize[1]);
            int[][] matrix = new int[countRows][countColumn];
            String[] line;
            for (int i = 0; i < countRows; i++) {
                line = reader.readLine().split(" ");
                for (int j = 0; j < countColumn; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }

            System.out.println(findResult(matrix, countRows, countColumn));
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static int findResult(int[][]matrix, int countRows, int countColumn){

        int[][] matrixSum = new int[countRows][countColumn];
        int curResult = 0;
        for (int i = 0; i < countColumn; i++) {
            curResult += matrix[0][i];
            matrixSum[0][i] = curResult;
        }

        for (int i = 1; i < countRows; i++) {
            for (int j = 0; j < countColumn; j++) {
                if(j == 0){
                    matrixSum[i][j] = matrixSum[i - 1][j] + matrix[i][j];
                } else {
                    matrixSum[i][j] = Math.min(matrixSum[i - 1][j], matrixSum[i][j - 1]) + matrix[i][j];
                }
            }
        }
        return matrixSum[countRows - 1][countColumn - 1];
    }
}