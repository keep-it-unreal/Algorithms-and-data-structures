package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String[] array = reader.readLine().split(" ");
            int countRows = Integer.parseInt(array[0]);
            int countColumn = Integer.parseInt(array[1]);
            int[] feeder = new int[]{Integer.parseInt(array[2]), Integer.parseInt(array[3])};
            int[][] matrix = new int[countRows + 1][countColumn + 1];
            int countFleas = Integer.parseInt(array[4]);
            int[][] arrayOfFleas = new int[countFleas][2];

            for (int i = 0; i < countFleas; i++) {
                array = reader.readLine().split(" ");
                arrayOfFleas[i] = new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])};
            }

            System.out.println(bfs(matrix, arrayOfFleas, feeder));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static int bfs(int[][]matrix, int[][] arrayOfFleas, int[] feeder) {
        int[] tryToStep;
        int[] curStep = feeder;

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = -1;
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(curStep);
        int remoteness;
        matrix[curStep[0]][curStep[1]] = 0;
        while (!queue.isEmpty()) {
            curStep = queue.poll();
            remoteness = matrix[curStep[0]][curStep[1]] + 1;

            if (curStep[0] + 2 < matrix.length) {
                if (curStep[1] + 1 < matrix[0].length) {
                    tryToStep = new int[]{curStep[0] + 2, curStep[1] + 1};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
                if (curStep[1] - 1 > -1) {
                    tryToStep = new int[]{curStep[0] + 2, curStep[1] - 1};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
            }
            if (curStep[0] - 2 > -1) {
                if (curStep[1] + 1 < matrix[0].length) {
                    tryToStep = new int[]{curStep[0] - 2, curStep[1] + 1};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
                if (curStep[1] - 1 > -1) {
                    tryToStep = new int[]{curStep[0] - 2, curStep[1] - 1};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
            }
            if (curStep[0] + 1 < matrix.length) {
                if (curStep[1] + 2 < matrix[0].length) {
                    tryToStep = new int[]{curStep[0] + 1, curStep[1] + 2};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
                if (curStep[1] - 2 > -1) {
                    tryToStep = new int[]{curStep[0] + 1, curStep[1] - 2};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
            }
            if (curStep[0] - 1 > -1) {
                if (curStep[1] + 2 < matrix[0].length) {
                    tryToStep = new int[]{curStep[0] - 1, curStep[1] + 2};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
                if (curStep[1] - 2 > -1) {
                    tryToStep = new int[]{curStep[0] - 1, curStep[1] - 2};
                    if (matrix[tryToStep[0]][tryToStep[1]] == -1) {
                        queue.add(tryToStep);
                        matrix[tryToStep[0]][tryToStep[1]] = remoteness;
                    }
                }
            }
        }

        int finalResult = 0;

        for (int[] curFleas : arrayOfFleas) {
            if (matrix[curFleas[0]][curFleas[1]] != -1) {
                finalResult += matrix[curFleas[0]][curFleas[1]];
            } else {
                return -1;
            }
        }
        return finalResult;
    }
}