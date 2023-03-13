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
            int n = Integer.parseInt(reader.readLine());
            int[][][] cube = new int[n + 1][n + 1][n + 1];
            int[] start = new int[3];
            String str;

            for (int i = 1; i < n + 1; i++) {
                reader.readLine();
                for (int j = 1; j < n + 1; j++) {
                    str = reader.readLine();
                    for (int k = 1; k < n + 1; k++) {
                        if(str.charAt(k - 1) == '#'){
                            cube[i][j][k] = -2;
                        } else if (str.charAt(k - 1) == '.') {
                            cube[i][j][k] = -1;
                        }else {
                            start = new int[]{i, j, k};
                        }
                    }
                }
            }

            System.out.println(bfs(cube, start));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static int bfs(int[][][]cube, int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        int remoteness;
        int[] curStep;
        int[] tryToStep;
        while (!queue.isEmpty()){
            curStep = queue.poll();
            remoteness = cube[curStep[0]][curStep[1]][curStep[2]] + 1;
            if(curStep[0] + 1 < cube.length && cube[curStep[0] + 1][curStep[1]][curStep[2]] == -1){
                tryToStep = new int[]{curStep[0] + 1, curStep[1], curStep[2]};
                queue.add(tryToStep);
                cube[curStep[0] + 1][curStep[1]][curStep[2]] = remoteness;
            }
            if(curStep[0] - 1 > 0 && cube[curStep[0] - 1][curStep[1]][curStep[2]] == -1){
                tryToStep = new int[]{curStep[0] - 1, curStep[1], curStep[2]};
                queue.add(tryToStep);
                cube[curStep[0] - 1][curStep[1]][curStep[2]] = remoteness;
            }
            if(curStep[1] + 1 < cube.length && cube[curStep[0]][curStep[1] + 1][curStep[2]] == -1){
                tryToStep = new int[]{curStep[0], curStep[1] + 1, curStep[2]};
                queue.add(tryToStep);
                cube[curStep[0]][curStep[1] + 1][curStep[2]] = remoteness;
            }
            if(curStep[1] - 1 > 0 && cube[curStep[0]][curStep[1] - 1][curStep[2]] == -1){
                tryToStep = new int[]{curStep[0], curStep[1] - 1, curStep[2]};
                queue.add(tryToStep);
                cube[curStep[0]][curStep[1] - 1][curStep[2]] = remoteness;
            }
            if(curStep[2] + 1 < cube.length && cube[curStep[0]][curStep[1]][curStep[2] + 1] == -1){
                tryToStep = new int[]{curStep[0], curStep[1], curStep[2] + 1};
                queue.add(tryToStep);
                cube[curStep[0]][curStep[1]][curStep[2] + 1] = remoteness;
            }
            if(curStep[2] - 1 > 0 && cube[curStep[0]][curStep[1]][curStep[2] - 1] == -1){
                tryToStep = new int[]{curStep[0], curStep[1], curStep[2] - 1};
                queue.add(tryToStep);
                cube[curStep[0]][curStep[1]][curStep[2] - 1] = remoteness;
            }
        }

        int smallestRoad = 30000;
        for (int j = 1; j < cube.length; j++) {
            for (int k = 1; k < cube.length; k++) {
                if(cube[1][j][k] > -1){
                    smallestRoad = Math.min(cube[1][j][k], smallestRoad);
                }
            }
        }
            return smallestRoad;
    }
}