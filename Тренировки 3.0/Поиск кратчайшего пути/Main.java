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
            int countVertex = Integer.parseInt(reader.readLine());
            int[][] matrix = new int[countVertex + 1][countVertex + 1];

            String[] array;
            for (int i = 1; i < countVertex + 1; i++) {
                array = reader.readLine().split(" ");
                for (int j = 1; j < countVertex + 1; j++) {
                    matrix[i][j] = Integer.parseInt(array[j - 1]);
                }
            }

            array = reader.readLine().split(" ");
            int start = Integer.parseInt(array[0]);
            int finish = Integer.parseInt(array[1]);

            System.out.println(bfs(matrix, start, finish));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static int bfs(int[][]matrix, int start, int finish){
        Map<Integer, Integer> mapOfRemoteness = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int curVertex;
        int remoteness = 0;
        mapOfRemoteness.put(start, remoteness);

        while (!queue.isEmpty()){
            curVertex = queue.poll();
            remoteness = mapOfRemoteness.get(curVertex) + 1;
            for (int i = 1; i < matrix.length; i++) {
                if(matrix[curVertex][i] == 1){
                    if(!mapOfRemoteness.containsKey(i)){
                        mapOfRemoteness.put(i, remoteness);
                        queue.add(i);
                    }
                }
            }
        }
        return mapOfRemoteness.getOrDefault(finish, -1);
    }
}