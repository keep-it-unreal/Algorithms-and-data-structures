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

            Map<Integer, int[]> mapOfRemoteness = bfs(matrix, start, finish);

            if(mapOfRemoteness.containsKey(finish)){
                if(mapOfRemoteness.get(finish)[0] == 0){
                    System.out.println(0);
                }else {
                    int parent = mapOfRemoteness.get(finish)[1];
                    List<Integer> road = new ArrayList<>();
                    road.add(finish);
                    while (parent != start){
                        road.add(parent);
                        parent = mapOfRemoteness.get(parent)[1];
                    }
                    road.add(start);
                    System.out.println(mapOfRemoteness.get(finish)[0]);
                    for (int i = road.size() - 1; i >= 0; i--) {
                        System.out.print(road.get(i) + " ");
                    }
                }
            }else {
                System.out.println(-1);
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, int[]> bfs(int[][]matrix, int start, int finish){
        Map<Integer, int[]> mapOfRemoteness = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int curVertex;
        int remoteness = 0;
        int[] remotenessAndParent = {0, 0};
        mapOfRemoteness.put(start, remotenessAndParent);

        while (!queue.isEmpty() && !mapOfRemoteness.containsKey(finish)){
            curVertex = queue.poll();
            remoteness = mapOfRemoteness.get(curVertex)[0] + 1;
            for (int i = 1; i < matrix.length; i++) {
                if(matrix[curVertex][i] == 1){
                    if(!mapOfRemoteness.containsKey(i)){
                        remotenessAndParent = new int[]{remoteness, curVertex};
                        mapOfRemoteness.put(i, remotenessAndParent);
                        queue.add(i);
                    }
                }
            }
        }
        return mapOfRemoteness;
    }
}