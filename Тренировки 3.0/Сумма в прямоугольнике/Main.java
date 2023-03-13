package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rowsCount = scanner.nextInt();
        int columnCount = scanner.nextInt();
        int requestCount = scanner.nextInt();

        int curSum;

        int[][] matrixOfSum = new int[rowsCount][columnCount];

        scanner.nextLine();

        curSum = 0;
        for (int i = 0; i < columnCount; i++) {
            curSum += scanner.nextInt();
            matrixOfSum[0][i] = curSum;
        }

        scanner.nextLine();

        for (int i = 1; i < rowsCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if(j == 0){
                    matrixOfSum[i][j] = matrixOfSum[i - 1][j] + scanner.nextInt();
                } else {
                    matrixOfSum[i][j] = matrixOfSum[i][j - 1] + matrixOfSum[i - 1][j] - matrixOfSum[i - 1][j - 1] + scanner.nextInt();
                }
            }
            scanner.nextLine();
        }

        int x1, y1, x2, y2;

        for (int i = 0; i < requestCount; i++) {
            x1 = scanner.nextInt();
            y1 = scanner.nextInt();
            x2 = scanner.nextInt();
            y2 = scanner.nextInt();

            if(x1 == 1 && y1 == 1){
                curSum = matrixOfSum[x2 - 1][y2 - 1];
            } else if (x1 == 1) {
                curSum = matrixOfSum[x2 - 1][y2 - 1] - matrixOfSum[x2 - 1][y1 - 2];
            } else if (y1 == 1) {
                curSum = matrixOfSum[x2 - 1][y2 - 1] - matrixOfSum[x1 - 2][y2 - 1];
            } else {
                curSum = matrixOfSum[x2 - 1][y2 - 1] - matrixOfSum[x1 - 2][y2 - 1] - matrixOfSum[x2 - 1][y1 - 2] + matrixOfSum[x1 - 2][y1 - 2];
            }
            System.out.println(curSum);
        }
    }
}