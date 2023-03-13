package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static int[][] matrix;
    static Map<Integer, Boolean> mapOfAttendance;

    static List<Integer> result;

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int countVertex = Integer.parseInt(reader.readLine());
            matrix = new int[countVertex + 1][countVertex + 1];
            mapOfAttendance = new HashMap<>();
            String[] array;

            for (int i = 1; i < countVertex + 1; i++) {
                array = reader.readLine().split(" ");
                mapOfAttendance.put(i, false);
                for (int j = 1; j < countVertex + 1; j++) {
                    matrix[i][j] = Integer.parseInt(array[j - 1]);
                }
            }

            result = new ArrayList<>();
            for (int i = 1; i < countVertex + 1; i++) {
                if(!mapOfAttendance.get(i)) {
                    dfs(i, -1);
                }
                if(result.size() != 0){
                    break;
                }
            }

            if(result.size() != 0){
                System.out.println("YES");
                System.out.println(result.size());
                for (int vertexInLoop: result) {
                    System.out.print(vertexInLoop + " ");
                }
            } else {
                System.out.println("NO");
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static int dfs(int curVertex, int prevVertex){
        if(mapOfAttendance.get(curVertex)){
            return curVertex;
        }

        mapOfAttendance.put(curVertex, true);
        int loopStart = -1;
        for (int i = 1; i < matrix.length; ++i) {
            if(matrix[curVertex][i] == 1 && i != prevVertex) {
                loopStart = dfs(i, curVertex);
                if(loopStart == -2){
                    return -2;
                }else if (loopStart > -1) {
                    result.add(curVertex);
                    if(curVertex == loopStart){
                        return -2;
                    } else {
                        return loopStart;
                    }
                }
            }
        }
        return loopStart;
    }
}