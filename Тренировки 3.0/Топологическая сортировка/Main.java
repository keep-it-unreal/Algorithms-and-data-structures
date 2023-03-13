package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static Map<Integer, Integer> mapOfAttendance;

    static List<Integer> result;

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String[] array = reader.readLine().split(" ");
            int countVertex = Integer.parseInt(array[0]);
            int countEdge = Integer.parseInt(array[1]);
            edges = new List[countVertex + 1];
            int firstValue, secondValue;
            mapOfAttendance = new HashMap<>();

            for (int i = 0; i < countEdge; i++) {
                array = reader.readLine().split(" ");
                firstValue = Integer.parseInt(array[0]);
                secondValue = Integer.parseInt(array[1]);
                if(edges[firstValue] == null){
                    edges[firstValue] = new ArrayList<>();
                    mapOfAttendance.put(firstValue, 0);
                }
                if(edges[secondValue] == null){
                    edges[secondValue] = new ArrayList<>();
                    mapOfAttendance.put(secondValue, 0);
                }
                edges[firstValue].add(secondValue);
            }


            result = new ArrayList<>();
            boolean isAbleSort = true;
            for (int i = 1; i <= countVertex; i++) {
                if(!mapOfAttendance.containsKey(i)){
                    mapOfAttendance.put(i, 0);
                    edges[i] = new ArrayList<>();
                }
                if(mapOfAttendance.get(i) == 0) {
                    isAbleSort = dfs(i);
                }
                if(!isAbleSort){
                    break;
                }
            }

            if(isAbleSort){
                for (int i = result.size() - 1; i >= 0; i--) {
                    System.out.print(result.get(i) + " ");
                }
            }else {
                System.out.println(-1);
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static boolean dfs(int curVertex){
        if(mapOfAttendance.get(curVertex) == 1){
            return false;
        } else if (mapOfAttendance.get(curVertex) == 2) {
            return true;
        } else if (edges[curVertex].size() == 0) {
            mapOfAttendance.put(curVertex, 2);
            result.add(curVertex);
            return true;
        }
        mapOfAttendance.put(curVertex, 1);
        boolean isAbleSort = true;
        for (Integer vertex:edges[curVertex]) {
            isAbleSort = dfs(vertex);
            if(!isAbleSort){
                break;
            }
        }
        mapOfAttendance.put(curVertex, 2);
        result.add(curVertex);
        return isAbleSort;
    }
}